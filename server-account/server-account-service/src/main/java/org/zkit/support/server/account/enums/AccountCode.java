package org.zkit.support.server.account.enums;

public enum AccountCode {

    ACCESS_AUTHORITY_EXIST(4000, "access.authority.exist"),

    ACCESS_ROLE_EXIST(4100, "access.role.exist");

    public final int code;
    public final String key;

    AccountCode(int code, String key){
        this.code = code;
        this.key = key;
    }

}
