package org.zkit.support.server.account.api.entity.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "重置密码请求")
public class ResetPasswordRequest {

    @Schema(description = "用户ID", hidden = true)
    private Long id;
    @Schema(description = "密码")
    private String password;

}