package org.zkit.support.server.account.api.entity.request;

import lombok.Data;

@Data
public class SetPasswordRequest {

    private Long id;
    private String password;
    private String code;

}
