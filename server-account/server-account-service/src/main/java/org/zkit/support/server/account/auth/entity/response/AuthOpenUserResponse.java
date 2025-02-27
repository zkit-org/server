package org.zkit.support.server.account.auth.entity.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.zkit.support.server.account.auth.entity.dto.AuthOpenUser;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "第三方用户信息")
public class AuthOpenUserResponse extends AuthOpenUser {

    @Schema(description = "令牌")
    private String token;
    @Schema(description = "失效时间")
    private Long expiresIn;
    @Schema(description = "是否绑定")
    private Boolean isBind;

}
