package org.zkit.support.server.account.api.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.zkit.support.server.account.api.constant.AccountApi;
import org.zkit.support.server.account.api.constant.AccountApiRoute;
import org.zkit.support.server.account.api.entity.request.*;
import org.zkit.support.server.account.api.entity.response.*;
import org.zkit.support.starter.boot.entity.Result;

@FeignClient(value = AccountApi.APP_NAME)
@Service
public interface AuthAccountOTPRestApi {

    @GetMapping(value = AccountApiRoute.AUTH_ACCOUNT_OTP_SECRET)
    Result<OTPResponse> otpSecret(@RequestParam("id") Long accountId);

    @GetMapping(value = AccountApiRoute.AUTH_ACCOUNT_OTP_STATUS)
    Result<OTPStatusResponse> otpState(@RequestParam("id") Long accountId);

    @PostMapping(value = AccountApiRoute.AUTH_ACCOUNT_OTP_BIND)
    Result<?> bind(@RequestBody OTPBindRequest request);

    @PutMapping(value = AccountApiRoute.AUTH_ACCOUNT_OTP_DISABLE)
    Result<?> disable(@RequestBody OTPDisableRequest request);

}
