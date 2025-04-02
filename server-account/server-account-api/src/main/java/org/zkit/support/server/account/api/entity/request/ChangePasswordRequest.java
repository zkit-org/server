package org.zkit.support.server.account.api.entity.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "修改密码请求")
public class ChangePasswordRequest implements Serializable {

    @Schema(description = "用户ID", hidden = true)
    private Long id;
    @Schema(description = "原始密码")
    private String originPassword;
    @Schema(description = "新密码")
    private String password;

}
