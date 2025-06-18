package org.zkit.support.server.assets.api;

import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboService;
import org.zkit.support.server.assets.api.entity.request.OSSPresignRequest;
import org.zkit.support.server.assets.api.entity.response.OSSPresignResponse;
import org.zkit.support.server.assets.api.service.AssetsOSSApiService;
import org.zkit.support.server.assets.oss.service.OssFileService;

@DubboService
public class AssetsOSSApiServiceImpl implements AssetsOSSApiService {

    @Resource
    private OssFileService ossFileService;

    @Override
    public OSSPresignResponse presign(OSSPresignRequest request) {
        return ossFileService.presign(request);
    }

    @Override
    public String sign(String url) {
        return ossFileService.sign(url);
    }
}
