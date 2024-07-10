package org.zkit.support.server.assets.oss.service.impl;

import jakarta.annotation.Resource;
import org.zkit.support.server.assets.alioss.service.AliOSSService;
import org.zkit.support.server.assets.oss.entity.dto.OssFile;
import org.zkit.support.server.assets.oss.entity.request.AliOSSPreSignRequest;
import org.zkit.support.server.assets.oss.entity.response.AliOSSPreSignResponse;
import org.zkit.support.server.assets.oss.mapper.OssFileMapper;
import org.zkit.support.server.assets.oss.service.OssFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

    public AliOSSPreSignResponse preSign(AliOSSPreSignRequest request) {
        AliOSSPreSignResponse response = new AliOSSPreSignResponse();
        String signedUrl = aliOSSService.sign(request.getFileName());
        response.setFileName(request.getFileName());
        response.setSignedUrl(signedUrl);
        response.setUrl(signedUrl);
        return response;
    }

}
