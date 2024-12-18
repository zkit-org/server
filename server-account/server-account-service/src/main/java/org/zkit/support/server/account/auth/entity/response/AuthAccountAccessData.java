package org.zkit.support.server.account.auth.entity.response;

import lombok.Data;
import org.zkit.support.starter.security.entity.SessionUser;

import java.util.List;

@Data
public class AuthAccountAccessData {

    private List<String> authorities;
    private List<SessionUser.Api> apis;

}
