package org.zkit.support.server.account.api.service;

import org.zkit.support.server.account.api.entity.request.OTPBindRequest;
import org.zkit.support.server.account.api.entity.request.OTPDisableRequest;
import org.zkit.support.server.account.api.entity.response.OTPResponse;
import org.zkit.support.server.account.api.entity.response.OTPStatusResponse;

public interface AccountOTPApiService {

    OTPResponse otpSecret(Long accountId);
    OTPStatusResponse otpState(Long accountId);
    void bind(OTPBindRequest request);
    void disable(OTPDisableRequest request);

}
