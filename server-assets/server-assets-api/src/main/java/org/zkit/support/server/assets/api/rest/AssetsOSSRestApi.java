package org.zkit.support.server.assets.api.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.zkit.support.server.assets.api.constant.AssetsApi;
import org.zkit.support.server.assets.api.constant.AssetsApiRoute;
import org.zkit.support.server.assets.api.entity.request.OSSSignRequest;
import org.zkit.support.starter.boot.entity.Result;

@FeignClient(value = AssetsApi.APP_NAME)
@Service
public interface AssetsOSSRestApi {

    @PostMapping(value = AssetsApiRoute.SIGN)
    Result<String> sign(@RequestBody() OSSSignRequest request);

}
