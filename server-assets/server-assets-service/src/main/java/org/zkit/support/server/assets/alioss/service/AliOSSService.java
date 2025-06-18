package org.zkit.support.server.assets.alioss.service;

import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.zkit.support.server.assets.alioss.configuration.AliOSSConfiguration;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class AliOSSService {

    @Resource
    private AliOSSConfiguration configuration;

    public String getBucket(Integer type) {
        return type == 0 ? configuration.getBucketPublic() : configuration.getBucketPrivate();
    }
    
    public String presign(String bucket, String objectName) {
        return presign(bucket, objectName, null, null);
    }

    public String presign(String bucket, String objectName, Map<String, String> headers) {
        return presign(bucket,objectName, headers, null);
    }

    public String presign(String bucket, String objectName, Map<String, String> headers, Map<String, String> metadata) {
        log.info("config {}", configuration);
        // 以华东1（杭州）的外网Endpoint为例，其它Region请按实际情况填写。
        String endpoint = configuration.getEndpoint();
        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        // 填写Bucket名称。
        String bucketName = bucket;
        // 填写Object完整路径。Object完整路径中不能包含Bucket名称。

        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, configuration.getKeyId(), configuration.getKeySecret());

        URL signedUrl = null;
        try {
            // 指定生成的签名URL过期时间，单位为毫秒。本示例以设置过期时间为1小时为例。
            Date expiration = new Date(new Date().getTime() + configuration.getExpireTime());

            // 生成签名URL。
            GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, objectName, HttpMethod.PUT);
            // 设置过期时间。
            request.setExpiration(expiration);

            // 将请求头加入到request中。
            request.setHeaders(headers == null ? new HashMap<>() : headers);
            // 添加用户自定义元数据。
            request.setUserMetadata(metadata == null ? new HashMap<>() : metadata);

            // 通过HTTP PUT请求生成签名URL。
            signedUrl = ossClient.generatePresignedUrl(request);
            // 打印签名URL。
            log.info("signed url for putObject: {}", signedUrl);
            return signedUrl.toString();
        } catch (OSSException oe) {
            log.error("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            log.error("Error Message:{}", oe.getErrorMessage());
            log.error("Error Code:{}", oe.getErrorCode());
            log.error("Request ID:{}", oe.getRequestId());
            log.error("Host ID:{}", oe.getHostId());
        } catch (ClientException ce) {
            log.error("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            log.error("Error Message:{}", ce.getMessage());
        }
        return null;
    }

    public void upload(String signedUrl, String pathName, Map<String, String> headers, Map<String, String> userMetadata) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            HttpPut put = new HttpPut(signedUrl);
            HttpEntity entity = new FileEntity(new File(pathName));
            put.setEntity(entity);
            // 如果生成签名URL时设置了header参数，例如用户元数据，存储类型等，则调用签名URL上传文件时，也需要将这些参数发送至服务端。如果签名和发送至服务端的不一致，会报签名错误。
            for(Map.Entry<?,?> header: headers.entrySet()){
                put.addHeader(header.getKey().toString(),header.getValue().toString());
            }
            for(Map.Entry<?,?> meta: userMetadata.entrySet()){
                // 如果使用userMeta，sdk内部会为userMeta拼接"x-oss-meta-"前缀。当您使用其他方式生成签名URL进行上传时，userMeta也需要拼接"x-oss-meta-"前缀。
                put.addHeader("x-oss-meta-"+meta.getKey().toString(), meta.getValue().toString());
            }

            httpClient = HttpClients.createDefault();

            response = httpClient.execute(put);

            log.info("返回上传状态码：{}", response.getStatusLine().getStatusCode());
            if(response.getStatusLine().getStatusCode() == 200){
                log.info("使用网络库上传成功");
            }
            log.info("response: {}", response);
            String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
            log.info(responseString);
        } catch (Exception e){
            log.error(e.getMessage());
        } finally {
            try{
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            }catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }

    public String sign(String bucket, String objectName) {
        OSS ossClient = null;
        try {
            String endpoint = configuration.getEndpoint(); 
            DefaultCredentialProvider credentialsProvider = CredentialsProviderFactory
                    .newDefaultCredentialProvider(configuration.getKeyId(), configuration.getKeySecret());
            String bucketName = bucket;
            String region = configuration.getRegion();

            ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
            clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
            ossClient = OSSClientBuilder.create()
                    .endpoint(endpoint)
                    .credentialsProvider(credentialsProvider)
                    .clientConfiguration(clientBuilderConfiguration)
                    .region(region)
                    .build();
            // 设置预签名URL过期时间，单位为毫秒。本示例以设置过期时间为1小时为例。
            Date expiration = new Date(new Date().getTime() + configuration.getExpireTime());
            // 生成以GET方法访问的预签名URL。本示例没有额外请求头，其他人可以直接通过浏览器访问相关内容。
            URL signedUrl = ossClient.generatePresignedUrl(bucketName, objectName, expiration);
            return signedUrl.toString();
        } catch (OSSException oe) {
            log.error("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            log.error("Error Message:" + oe.getErrorMessage());
            log.error("Error Code:" + oe.getErrorCode());
            log.error("Request ID:" + oe.getRequestId());
            log.error("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            log.error("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            log.error("Error Message:" + ce.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return null;
    }

}
