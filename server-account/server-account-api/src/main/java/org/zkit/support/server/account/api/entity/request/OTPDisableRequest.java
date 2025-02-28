package org.zkit.support.server.account.api.entity.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "禁用OTP请求")
public class OTPDisableRequest {

    @Schema(description = "用户ID")
    private Long id;

}
