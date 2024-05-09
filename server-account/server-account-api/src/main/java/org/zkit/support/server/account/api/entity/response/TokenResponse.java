package org.zkit.support.server.account.api.entity.response;

import lombok.Data;

@Data
public class TokenResponse {

    private String token;
    private Long expiresIn;
    private String username;

}
