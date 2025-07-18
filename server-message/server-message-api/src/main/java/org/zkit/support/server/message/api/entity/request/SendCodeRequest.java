package org.zkit.support.server.message.api.entity.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "发送验证码请求")
public class SendCodeRequest implements Serializable {

    @Schema(description = "邮箱")
    private String email;
    @Schema(description = "操作")
    private String action;

}
