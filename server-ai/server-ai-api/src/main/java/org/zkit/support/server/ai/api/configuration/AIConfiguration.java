package org.zkit.support.server.ai.api.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "ai")
@Data
public class AIConfiguration {

    private String baseUrl;
    private Integer limit;
    private String invokeUrl = "/chain/invoke";
    private String streamUrl = "/chain/stream";

}
