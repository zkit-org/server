package org.zkit.support.server.ai.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatMessage {

    @Schema(description = "角色")
    private String role;
    @Schema(description = "消息内容")
    private String content;

}
