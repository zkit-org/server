package org.zkit.support.server.account.api;

import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboService;
import org.zkit.support.server.account.api.entity.request.OTPBindRequest;
import org.zkit.support.server.account.api.entity.request.OTPDisableRequest;
import org.zkit.support.server.account.api.entity.response.OTPResponse;
import org.zkit.support.server.account.api.entity.response.OTPStatusResponse;
import org.zkit.support.server.account.api.service.AuthAccountOTPApiService;
import org.zkit.support.server.account.auth.service.AuthAccountService;

@DubboService
public class AuthAccountOTPApiServiceImpl implements AuthAccountOTPApiService {

    @Resource
    private AuthAccountService authAccountService;

    @Override
    public OTPResponse otpSecret(Long accountId) {
        return authAccountService.otpSecret(accountId);
    }

    @Override
    public OTPStatusResponse otpState(Long accountId) {
        return authAccountService.otpStatus(accountId);
    }

    @Override
    public void bind(OTPBindRequest request) {
        authAccountService.bindOTP(request);
    }

    @Override
    public void disable(OTPDisableRequest request) {
        authAccountService.disableOTP(request.getId());
    }
}
