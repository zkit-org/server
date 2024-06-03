package org.zkit.support.server.openai.api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "翻译请求")
public class TranslatorRequest {

    @Schema(description = "消息")
    private String message;

    @Schema(description = "目标")
    private String target;

}
