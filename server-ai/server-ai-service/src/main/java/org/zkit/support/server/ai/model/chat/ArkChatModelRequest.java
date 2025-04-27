package org.zkit.support.server.ai.model.chat;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArkChatModelRequest {
    private String model;
    private List<ArkChatModelMessage> messages;
    private Double temperature;
}
