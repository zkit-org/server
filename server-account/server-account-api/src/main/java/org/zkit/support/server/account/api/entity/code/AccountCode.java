package org.zkit.support.server.account.api.entity.code;

public enum AccountCode {

    ADD_OTP_ERROR(10001, "account.add.otp.error"),
    OTP_IS_BIND(10002, "account.otp.is.bind");

    public final int code;
    public final String key;

    AccountCode(int code, String key){
        this.code = code;
        this.key = key;
    }

}
