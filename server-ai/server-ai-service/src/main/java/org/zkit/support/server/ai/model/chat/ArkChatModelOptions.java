package org.zkit.support.server.ai.model.chat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArkChatModelOptions {
    
    private String baseUrl;
    private String apiKey;
    private String model;
    private double temperature;


}
