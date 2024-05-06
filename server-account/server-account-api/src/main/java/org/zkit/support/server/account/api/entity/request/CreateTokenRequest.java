package org.zkit.support.server.account.api.entity.request;

import lombok.Data;

@Data
public class CreateTokenRequest {

    private Long id;
    private Long expiresIn;

}
