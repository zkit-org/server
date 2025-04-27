package org.zkit.support.server.ai.model.chat;

import java.util.List;

import lombok.Data;

@Data
public class ArkChatModelResponse {
    private List<ArkChatModelChoice> choices;
}

