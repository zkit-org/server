package org.zkit.support.server.ai.api.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.zkit.support.server.ai.api.constant.AIApi;
import org.zkit.support.server.ai.api.constant.AIApiRoute;
import org.zkit.support.server.ai.api.entity.TranslatorRequest;
import org.zkit.support.starter.boot.entity.Result;

import java.util.List;

@FeignClient(value = AIApi.APP_NAME)
@Service
public interface AIRestApi {

    @GetMapping(value = AIApiRoute.TRANSLATOR)
    Result<List<String>> translator(@RequestBody() TranslatorRequest request);

}
