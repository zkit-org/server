package org.zkit.support.server.ai.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "ai")
@Data
public class AIConfiguration {

    private String model;
    private String host;
    private String apiKey;
    private String chatApi;

}
