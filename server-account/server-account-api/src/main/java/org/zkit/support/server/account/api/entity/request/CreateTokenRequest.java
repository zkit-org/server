package org.zkit.support.server.account.api.entity.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "创建令牌请求")
public class CreateTokenRequest implements Serializable {

    @Schema(description = "用户ID")
    private Long id;
    @Schema(description = "过期时间")
    private Long expiresIn;

}
