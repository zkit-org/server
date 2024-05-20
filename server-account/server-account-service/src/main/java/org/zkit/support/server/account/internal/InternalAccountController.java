package org.zkit.support.server.account.internal;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.zkit.support.server.account.api.constant.AccountApiRoute;
import org.zkit.support.server.account.api.entity.request.AccountAddRequest;
import org.zkit.support.server.account.api.entity.request.AccountLoginRequest;
import org.zkit.support.server.account.api.entity.request.CreateTokenRequest;
import org.zkit.support.server.account.api.entity.request.SetPasswordRequest;
import org.zkit.support.server.account.api.entity.response.AccountResponse;
import org.zkit.support.server.account.api.entity.response.OTPResponse;
import org.zkit.support.server.account.api.entity.response.TokenResponse;
import org.zkit.support.server.account.auth.entity.dto.AuthAccount;
import org.zkit.support.server.account.auth.entity.mapstruct.AuthAccountMapStruct;
import org.zkit.support.server.account.auth.service.AuthAccountService;
import org.zkit.support.starter.boot.service.SessionService;

@RestController
@Slf4j
@Tag(name = "internal-account", description = "[内部接口]用户账户")
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

    @PostMapping(AccountApiRoute.AUTH_ACCOUNT_OTP_SECRET)
    @Operation(summary = "获取OTP密钥")
    public OTPResponse otpSecret(@RequestParam("id") Long id) {
        return authAccountService.otpSecret(id);
    }

    @PostMapping(AccountApiRoute.AUTH_ACCOUNT_SET_PASSWORD)
    @Operation(summary = "设置密码")
    public TokenResponse setPassword(@RequestBody() SetPasswordRequest request) {
        return authAccountService.setPassword(request);
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
}
