package org.zkit.support.server.account.api.entity.response;

import lombok.Data;

@Data
public class OTPResponse {

    private String secret;
    private String qrcode;
    private String username;

}
