package org.zkit.support.server.account.api.entity.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "账户响应")
public class AccountResponse {

    @Schema(description = "ID")
    private Long id;
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "创建时间")
    private Date createTime;
    @Schema(description = "状态")
    private Integer status;
    @Schema(description = "OTP状态")
    private Integer otpStatus;
    @Schema(description = "删除标记", hidden = true)
    private Boolean deleted;

}
