package org.zkit.support.server.account.api.entity.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "OTP状态")
public class OTPStatusResponse {

    @Schema(description = "启用状态")
    private Boolean enable;
    @Schema(description = "启用时间")
    private Date enableTime;

}
