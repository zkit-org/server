package org.zkit.support.server.account.inner.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zkit.support.server.account.api.constant.AccountApiRoute;
import org.zkit.support.server.account.api.entity.request.AccountAddRequest;
import org.zkit.support.server.account.api.entity.request.CreateTokenRequest;
import org.zkit.support.server.account.api.entity.response.AccountResponse;
import org.zkit.support.server.account.api.entity.response.TokenResponse;
import org.zkit.support.server.account.auth.entity.dto.AuthAccount;
import org.zkit.support.server.account.auth.entity.mapstruct.AuthAccountMapStruct;
import org.zkit.support.server.account.auth.service.AuthAccountService;

@RestController
@Slf4j
public class InnerAccountController {

    private AuthAccountService authAccountService;
    private AuthAccountMapStruct authAccountMapStruct;

    @GetMapping(AccountApiRoute.AUTH_FIND_BY_USERNAME)
    public AccountResponse findByUsername(@RequestParam("username") String username) {
        AuthAccount account = authAccountService.findByUsername(username);
        return authAccountMapStruct.toAccountResponse(account);
    }

    @PostMapping(AccountApiRoute.AUTH_ACCOUNT_ADD)
    public AccountResponse add(@RequestBody() AccountAddRequest request) {
        AuthAccount account = authAccountService.add(authAccountMapStruct.toAuthAccount(request));
        return authAccountMapStruct.toAccountResponse(account);
    }

    @PostMapping(AccountApiRoute.AUTH_CREATE_TOKEN)
    public TokenResponse createToken(@RequestBody() CreateTokenRequest request) {
        return authAccountService.createToken(request);
    }

    @Autowired
    public void setAuthAccountService(AuthAccountService authAccountService) {
        this.authAccountService = authAccountService;
    }

    @Autowired
    public void setAuthAccountMapStruct(AuthAccountMapStruct authAccountMapStruct) {
        this.authAccountMapStruct = authAccountMapStruct;
    }
}
