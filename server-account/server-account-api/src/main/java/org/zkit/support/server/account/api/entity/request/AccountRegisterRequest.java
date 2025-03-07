package org.zkit.support.server.account.api.entity.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "注册")
public class AccountRegisterRequest {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

}
