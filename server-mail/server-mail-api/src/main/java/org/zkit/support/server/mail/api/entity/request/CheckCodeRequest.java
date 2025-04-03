package org.zkit.support.server.mail.api.entity.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "校验验证码")
public class CheckCodeRequest implements Serializable {

    @Schema(description = "邮箱")
    private String email;
    @Schema(description = "操作")
    private String action;
    @Schema(description = "验证码")
    private String code;

}
