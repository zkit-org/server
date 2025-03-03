package org.zkit.support.server.account.internal;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.zkit.support.server.account.api.constant.AccountApiRoute;
import org.zkit.support.server.account.api.entity.request.*;
import org.zkit.support.server.account.api.entity.response.AccountResponse;
import org.zkit.support.server.account.api.entity.response.OTPResponse;
import org.zkit.support.server.account.api.entity.response.TokenResponse;
import org.zkit.support.server.account.api.entity.response.TokenWithAccountResponse;
import org.zkit.support.server.account.auth.entity.dto.AuthAccount;
import org.zkit.support.server.account.auth.entity.mapstruct.AuthAccountMapStruct;
import org.zkit.support.server.account.auth.service.AuthAccountService;
import org.zkit.support.starter.security.service.SessionService;

@RestController
@Slf4j
@Tag(name = "InternalAccountController", description = "用户账户")
public class InternalAccountController {

    @Resource
    private AuthAccountService authAccountService;
    @Resource
    private AuthAccountMapStruct authAccountMapStruct;
    @Resource
    private SessionService sessionService;

    @Operation(summary = "根据用户名查找账户")
    @GetMapping(AccountApiRoute.AUTH_FIND_BY_USERNAME)
    public AccountResponse findByUsername(@Parameter(description = "用户名") @RequestParam("username") String username) {
        AuthAccount account = authAccountService.findByUsername(username);
        return authAccountMapStruct.toAccountResponse(account);
    }

    @PostMapping(AccountApiRoute.AUTH_ACCOUNT_ADD)
    @Operation(summary = "添加账户")
    public AccountResponse add(@RequestBody() AccountAddRequest request) {
        AuthAccount account = authAccountService.add(authAccountMapStruct.toAuthAccount(request));
        return authAccountMapStruct.toAccountResponse(account);
    }

    @PostMapping(AccountApiRoute.AUTH_ACCOUNT_ADD_OR_GET)
    @Operation(summary = "添加或获取账户")
    public AccountResponse addOrGet(@RequestBody() AccountAddRequest request) {
        AuthAccount account = authAccountService.addOrGet(authAccountMapStruct.toAuthAccount(request));
        return authAccountMapStruct.toAccountResponse(account);
    }

    @PostMapping(AccountApiRoute.AUTH_CREATE_TOKEN)
    @Operation(summary = "创建Token")
    public TokenResponse createToken(@RequestBody() CreateTokenRequest request) {
        return authAccountService.createToken(request);
    }

    @PostMapping(AccountApiRoute.AUTH_ACCOUNT_REGISTER)
    @Operation(summary = "注册")
    public TokenWithAccountResponse register(@RequestBody() AccountRegisterRequest request) {
        return authAccountService.register(request);
    }

    @PostMapping(AccountApiRoute.AUTH_ACCOUNT_LOGIN)
    @Operation(summary = "登录")
    public TokenResponse login(@RequestBody() AccountLoginRequest request) {
        return authAccountService.login(request);
    }

    @PostMapping(AccountApiRoute.AUTH_ACCOUNT_LOGOUT)
    @Operation(summary = "退出")
    public void logout(@Parameter(description = "令牌") @RequestParam("token") String token) {
        sessionService.logout(token);
    }

    @PostMapping(AccountApiRoute.AUTH_ACCOUNT_RESET_PASSWORD)
    @Operation(summary = "重置密码")
    public TokenResponse setPassword(@RequestBody() ResetPasswordRequest request) {
        return authAccountService.resetPassword(request);
    }

    @PostMapping(AccountApiRoute.AUTH_ACCOUNT_CHANGE_PASSWORD)
    @Operation(summary = "修改密码")
    public void changePassword(@RequestBody() ChangePasswordRequest request) {
        authAccountService.changePassword(request);
    }
}
