package org.zkit.support.server.account.auth.entity.enums;

public enum AuthAccountEnum {

    STATUS_INACTIVATED(0),
    STATUS_NORMAL(1),
    STATUS_DISABLED(2);

    public final int code;

    AuthAccountEnum(int code){
        this.code = code;
    }

}
