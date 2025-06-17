package org.zkit.support.server.assets.oss.service.impl;

import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;

import org.zkit.support.server.assets.alioss.service.AliOSSService;
import org.zkit.support.server.assets.api.entity.request.OSSPresignRequest;
import org.zkit.support.server.assets.api.entity.response.OSSPresignResponse;
import org.zkit.support.server.assets.configuration.OSSConfiguration;
import org.zkit.support.server.assets.oss.entity.dto.OssFile;
import org.zkit.support.server.assets.oss.mapper.OssFileMapper;
import org.zkit.support.server.assets.oss.service.OssFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.stereotype.Service;
import org.zkit.support.starter.boot.utils.MD5Utils;

import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author generator
 * @since 2024-07-10
 */
@Service
public class OssFileServiceImpl extends ServiceImpl<OssFileMapper, OssFile> implements OssFileService {

    @Resource
    private AliOSSService aliOSSService;
    @Resource
    private OSSConfiguration configuration;

    @Transactional
    public OSSPresignResponse presign(OSSPresignRequest request) {
        if(request.getType() == null) {
            throw new RuntimeException("Type is required");
        }
        if(request.getFileName() == null) {
            throw new RuntimeException("FileName is required");
        }
        if(request.getUserId() == null) {
            throw new RuntimeException("UserId is required");
        }

        String hash = MD5Utils.text(UUID.randomUUID().toString());
        OSSPresignResponse response = new OSSPresignResponse();

        Map<String, String> headers = request.getHeaders();
        String contentType = null;
        String length = null;
        if(headers != null) {
            // 转换为小写进行匹配，处理用户传入不规范的header名称
            Map<String, String> lowerCaseHeaders = new java.util.HashMap<>();
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                lowerCaseHeaders.put(entry.getKey().toLowerCase(), entry.getValue());
            }
            contentType = lowerCaseHeaders.get("content-type");
            length = lowerCaseHeaders.get("content-length");
        }
        if(contentType == null) {
            contentType = MediaTypeFactory.getMediaType(request.getFileName()).orElse(MediaType.APPLICATION_OCTET_STREAM).toString();
        }
        if(length == null) {
            throw new RuntimeException("Content-Length is required");
        }

        String signedUrl = null;
        if(configuration.getProvider().equals("alioss")) {
            signedUrl = aliOSSService.presign(configuration.getPrefix() + hash, request.getType(), headers, request.getMetadata());
        } else {
            throw new RuntimeException("Provider not supported");
        }
        if(signedUrl == null) {
            throw new RuntimeException("OSS sign error");
        }
        String url = signedUrl.split("\\?")[0];

        OssFile file = new OssFile();
        file.setFileName(request.getFileName());
        file.setUrl(url);
        file.setLength(Long.parseLong(length));
        file.setContentType(contentType);
        file.setUserId(request.getUserId());
        file.setHash(hash);
        save(file);

        response.setFileName(request.getFileName());
        response.setSignedUrl(signedUrl);
        response.setUrl(url);

        return response;
    }

}
