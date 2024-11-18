package org.zkit.support.server.account.enums;

public enum AccountCode {

    ACCESS_AUTHORITY_ADD_EXIST(4000, "access.authority.add.exist");

    public final int code;
    public final String key;

    AccountCode(int code, String key){
        this.code = code;
        this.key = key;
    }

}
