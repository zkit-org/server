package org.zkit.support.server.account.auth.service;

public interface AuthOTPService {

    public boolean check(String username, String code);

}
