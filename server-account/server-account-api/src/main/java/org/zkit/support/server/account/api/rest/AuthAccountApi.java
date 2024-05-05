package org.zkit.support.server.account.api.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zkit.support.server.account.api.constant.AccountApi;
import org.zkit.support.server.account.api.constant.AccountApiRoute;
import org.zkit.support.server.account.api.entity.AccountResponse;

@FeignClient(value = AccountApi.APP_NAME)
@Service
public interface AuthAccountApi {

    @GetMapping(value = AccountApiRoute.FIND_BY_USERNAME)
    AccountResponse findByUsername(@RequestParam("username") String username);

}
