package org.zkit.support.server.account.api.entity.code;

public enum AccountCode {

    ADD_OTP_ERROR(10001, "account.add.otp.error"),
    OTP_IS_BIND(10002, "account.otp.is.bind"),
    OTP_ERROR(10003, "account.otp.error"),
    LOGIN_ERROR(10004, "account.login.error"),
    NOTFOUND(10005, "account.notfound"),
    REGISTER_HAS(10006, "account.register.has"),
    PASSWORD_ERROR(10007, "account.password.error"),
    OTP_SECRET_EXPIRED(10008, "account.otp.secret.expired"),
    OTP_REQUIRED(10009, "account.otp.required"),;

    public final int code;
    public final String key;

    AccountCode(int code, String key){
        this.code = code;
        this.key = key;
    }

}
