package org.zkit.support.server.ai.model.chat;

import lombok.Data;

@Data
public class ArkChatModelChoice {
    private ArkChatModelMessage message;
    private String finish_reason;
}
