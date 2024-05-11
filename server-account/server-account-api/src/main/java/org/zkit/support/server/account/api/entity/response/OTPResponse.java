package org.zkit.support.server.account.api.entity.response;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "OTP响应")
public class OTPResponse {

    @Schema(description = "秘钥")
    private String secret;
    @Schema(description = "OTP地址")
    private String qrcode;
    @Schema(description = "用户名")
    private String username;

}
