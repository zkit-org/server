package org.zkit.support.server.mail.api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Map;

@Data
@Schema(description = "邮件发送请求")
public class MailSendRequest {

    @Schema(description = "收件人")
    private String to;
    @Schema(description = "模板")
    private String template;
    @Schema(description = "数据")
    private Map<String, Object> data;
    @Schema(description = "语言")
    private String language;

}
