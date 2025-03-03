package org.zkit.support.server.account.internal;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.zkit.support.server.account.api.constant.AccountApiRoute;
import org.zkit.support.server.account.api.entity.request.*;
import org.zkit.support.server.account.api.entity.response.*;
import org.zkit.support.server.account.auth.entity.dto.AuthAccount;
import org.zkit.support.server.account.auth.entity.mapstruct.AuthAccountMapStruct;
import org.zkit.support.server.account.auth.service.AuthAccountService;
import org.zkit.support.starter.security.service.SessionService;

@RestController
@Slf4j
@Tag(name = "InternalAccountOTPController", description = "用户OTP设置")
public class InternalAccountOTPController {

    @Resource
    private AuthAccountService authAccountService;

    @GetMapping(AccountApiRoute.AUTH_ACCOUNT_OTP_SECRET)
    @Operation(summary = "获取OTP密钥")
    public OTPResponse otpSecret(@RequestParam("id") Long accountId) {
        return authAccountService.otpSecret(accountId);
    }

    @GetMapping(AccountApiRoute.AUTH_ACCOUNT_OTP_STATUS)
    @Operation(summary = "获取OTP状态")
    public OTPStatusResponse otpStatus(@RequestParam("id") Long accountId) {
        return authAccountService.otpStatus(accountId);
    }

    @PostMapping(AccountApiRoute.AUTH_ACCOUNT_OTP_BIND)
    @Operation(summary = "绑定OTP")
    public void bind(@RequestBody OTPBindRequest request) {
        authAccountService.bindOTP(request);
    }

    @PutMapping(AccountApiRoute.AUTH_ACCOUNT_OTP_DISABLE)
    @Operation(summary = "禁用OTP")
    public void disable(@RequestBody OTPDisableRequest request) {
        authAccountService.disableOTP(request.getId());
    }

}
