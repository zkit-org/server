package org.zkit.support.server.account.inner.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zkit.support.server.account.api.constant.AccountApiRoute;
import org.zkit.support.server.account.api.entity.AccountResponse;
import org.zkit.support.server.account.auth.entity.dto.AuthAccount;
import org.zkit.support.server.account.auth.entity.mapstruct.AuthAccountMapStruct;
import org.zkit.support.server.account.auth.service.AuthAccountService;

@RestController
@Slf4j
public class InnerAccountController {

    private AuthAccountService authAccountService;
    private AuthAccountMapStruct authAccountMapStruct;

    @GetMapping(AccountApiRoute.FIND_BY_USERNAME)
    public AccountResponse findByUsername(@RequestParam("username") String username) {
        log.info("InnerAccountController findByUsername username {}", username);
        AuthAccount account = authAccountService.test(username);
        log.info("InnerAccountController findByUsername account {}", account);
        return authAccountMapStruct.toAccountResponse(account);
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
