package org.zkit.support.server.assets.oss.service.impl;

import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.zkit.support.server.assets.alioss.service.AliOSSService;
import org.zkit.support.server.assets.oss.entity.dto.OssFile;
import org.zkit.support.server.assets.oss.entity.request.AliOSSPreSignRequest;
import org.zkit.support.server.assets.oss.entity.response.AliOSSPreSignResponse;
import org.zkit.support.server.assets.oss.mapper.OssFileMapper;
import org.zkit.support.server.assets.oss.service.OssFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.zkit.support.starter.boot.utils.MD5Utils;

import java.util.HashMap;
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

    @Transactional
    public AliOSSPreSignResponse preSign(AliOSSPreSignRequest request) {
        String hash = MD5Utils.text(UUID.randomUUID().toString());
        AliOSSPreSignResponse response = new AliOSSPreSignResponse();
        String contentType = request.getContentType();
        if(contentType == null) {
            contentType = MediaTypeFactory.getMediaType(response.getFileName()).orElse(MediaType.APPLICATION_OCTET_STREAM).toString();
        }

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", contentType);
        String signedUrl = aliOSSService.sign(hash, headers);
        if(signedUrl == null) {
            throw new RuntimeException("OSS sign error");
        }
        String url = signedUrl.split("\\?")[0];

        OssFile file = new OssFile();
        file.setFileName(request.getFileName());
        file.setContentType(contentType);
        file.setLength(request.getLength());
        file.setUrl(url);
        file.setUserId(request.getUserId());
        file.setHash(hash);
        save(file);

        response.setFileName(request.getFileName());
        response.setSignedUrl(signedUrl);
        response.setUrl(url);

        return response;
    }

}
