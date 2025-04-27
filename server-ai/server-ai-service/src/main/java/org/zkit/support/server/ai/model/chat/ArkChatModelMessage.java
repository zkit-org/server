package org.zkit.support.server.ai.model.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArkChatModelMessage {

    private String role;
    private String content;
    
}
