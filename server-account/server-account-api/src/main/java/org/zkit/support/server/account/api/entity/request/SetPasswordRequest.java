package org.zkit.support.server.account.api.entity.request;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "设置密码请求")
public class SetPasswordRequest {

    @Schema(description = "用户ID", hidden = true)
    private Long id;
    @Schema(description = "密码")
    private String password;
    @Schema(description = "验证码")
    private String code;

}
