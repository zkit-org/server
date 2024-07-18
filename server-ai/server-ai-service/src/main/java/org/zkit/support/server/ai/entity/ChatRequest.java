package org.zkit.support.server.ai.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class ChatRequest {

    @Schema(description = "Model ID, 可以通过 List Models 获取")
    private String model;
    @Schema(description = "包含迄今为止对话的消息列表")
    private List<ChatMessage> messages;
    @Schema(description = "使用什么采样温度，介于 0 和 1 之间。较高的值（如 0.7）将使输出更加随机，而较低的值（如 0.2）将使其更加集中和确定性")
    private Double temperature;

}
