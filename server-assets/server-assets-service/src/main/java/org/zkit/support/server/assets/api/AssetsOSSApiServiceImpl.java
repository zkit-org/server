package org.zkit.support.server.assets.api;

import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboService;
import org.zkit.support.server.assets.alioss.service.AliOSSService;
import org.zkit.support.server.assets.api.entity.request.OSSSignRequest;
import org.zkit.support.server.assets.api.service.AssetsOSSApiService;

@DubboService
public class AssetsOSSApiServiceImpl implements AssetsOSSApiService {

    @Resource
    private AliOSSService aliOSSService;


    @Override
    public String sign(OSSSignRequest request) {
        return aliOSSService.sign(request.getFileName(), request.getHeaders(), request.getMetadata());
    }
}
