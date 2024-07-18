package org.zkit.support.server.ai.entity;

import lombok.Data;

@Data
public class ChatChoice {

    private Integer index;
    private ChatMessage message;
    private String finish_reason;

}
