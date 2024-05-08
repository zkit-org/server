package org.zkit.support.server.account.api.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.zkit.support.cloud.starter.entity.Result;
import org.zkit.support.server.account.api.constant.AccountApi;
import org.zkit.support.server.account.api.constant.AccountApiRoute;
import org.zkit.support.server.account.api.entity.request.AccountAddRequest;
import org.zkit.support.server.account.api.entity.request.CreateTokenRequest;
import org.zkit.support.server.account.api.entity.response.AccountResponse;
import org.zkit.support.server.account.api.entity.response.TokenResponse;

@FeignClient(value = AccountApi.APP_NAME)
@Service
public interface AuthAccountApi {

    @GetMapping(value = AccountApiRoute.AUTH_FIND_BY_USERNAME)
    Result<AccountResponse> findByUsername(@RequestParam("username") String username);

    @PostMapping(value = AccountApiRoute.AUTH_ACCOUNT_ADD)
    Result<AccountResponse> add(@RequestBody() AccountAddRequest request);

    @PostMapping(value = AccountApiRoute.AUTH_ACCOUNT_ADD_OR_GET)
    Result<AccountResponse> addOrGet(@RequestBody() AccountAddRequest request);

    @PostMapping(value = AccountApiRoute.AUTH_CREATE_TOKEN)
    Result<TokenResponse> createToken(@RequestBody() CreateTokenRequest request);

}
