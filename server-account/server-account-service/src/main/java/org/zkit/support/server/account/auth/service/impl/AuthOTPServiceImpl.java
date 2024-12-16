package org.zkit.support.server.account.auth.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.zkit.support.server.account.auth.service.AuthOTPService;
import org.zkit.support.server.account.configuration.AccountConfiguration;
import org.zkit.support.starter.security.otp.OTPService;

@Service
public class AuthOTPServiceImpl implements AuthOTPService {

    @Resource
    private AccountConfiguration configuration;
    @Resource
    private OTPService otpService;

    @Override
    public boolean check(String username, String code) {
        if(configuration.isDebug()) {
            return configuration.getOtpCode().equals(code);
        }
        return otpService.check(username, code);
    }
}
