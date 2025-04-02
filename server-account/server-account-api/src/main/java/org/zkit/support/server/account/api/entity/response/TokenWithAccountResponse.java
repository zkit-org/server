package org.zkit.support.server.account.api.entity.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "令牌响应")
public class TokenWithAccountResponse extends TokenResponse implements Serializable {

    @Schema(description = "账号ID")
    private Long accountId;
    @Schema(description = "用户名")
    private String username;

}
