package org.zkit.support.server.account.api.entity.response;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "令牌响应")
public class TokenResponse {

    @Schema(description = "令牌")
    private String token;
    @Schema(description = "失效时间")
    private Long expiresIn;
    @Schema(description = "用户名")
    private String username;

}
