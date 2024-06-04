package org.zkit.support.server.account.auth.entity.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "绑定外部账户")
public class AuthLinkBindRequest {

    @Schema(description = "账号")
    private String account;
    @Schema(description = "密码")
    private String password;
    @Schema(description = "临时请求码")
    private String token;

}
