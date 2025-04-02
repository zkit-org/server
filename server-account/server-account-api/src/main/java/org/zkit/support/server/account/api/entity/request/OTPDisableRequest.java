package org.zkit.support.server.account.api.entity.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "禁用OTP请求")
public class OTPDisableRequest implements Serializable {

    @Schema(description = "用户ID")
    private Long id;

}
