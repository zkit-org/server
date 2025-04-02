package org.zkit.support.server.account.api;

import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboService;
import org.zkit.support.server.account.api.entity.request.*;
import org.zkit.support.server.account.api.entity.response.AccountResponse;
import org.zkit.support.server.account.api.entity.response.TokenResponse;
import org.zkit.support.server.account.api.entity.response.TokenWithAccountResponse;
import org.zkit.support.server.account.api.service.AuthAccountApiService;
import org.zkit.support.server.account.auth.entity.dto.AuthAccount;
import org.zkit.support.server.account.auth.entity.mapstruct.AuthAccountMapStruct;
import org.zkit.support.server.account.auth.service.AuthAccountService;
import org.zkit.support.starter.security.service.SessionService;

@DubboService
public class AuthAccountApiServiceImpl implements AuthAccountApiService {

    @Resource
    private AuthAccountService authAccountService;
    @Resource
    private AuthAccountMapStruct authAccountMapStruct;
    @Resource
    private SessionService sessionService;

    @Override
    public AccountResponse findByUsername(String username) {
        AuthAccount account = authAccountService.findByUsername(username);
        return authAccountMapStruct.toAccountResponse(account);
    }

    @Override
    public AccountResponse add(AccountAddRequest request) {
        AuthAccount account = authAccountService.add(authAccountMapStruct.toAuthAccount(request));
        return authAccountMapStruct.toAccountResponse(account);
    }

    @Override
    public AccountResponse addOrGet(AccountAddRequest request) {
        AuthAccount account = authAccountService.addOrGet(authAccountMapStruct.toAuthAccount(request));
        return authAccountMapStruct.toAccountResponse(account);
    }

    @Override
    public TokenResponse createToken(CreateTokenRequest request) {
        return authAccountService.createToken(request);
    }

    @Override
    public TokenWithAccountResponse register(AccountRegisterRequest request) {
        return authAccountService.register(request);
    }

    @Override
    public TokenResponse login(AccountLoginRequest request) {
        return authAccountService.login(request);
    }

    @Override
    public void logout(String token) {
        sessionService.logout(token);
    }

    @Override
    public TokenResponse resetPassword(ResetPasswordRequest request) {
        return authAccountService.resetPassword(request);
    }

    @Override
    public void changePassword(ChangePasswordRequest request) {
        authAccountService.changePassword(request);
    }

}
