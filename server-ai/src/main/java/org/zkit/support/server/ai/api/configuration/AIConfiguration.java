package org.zkit.support.server.ai.api.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.zkit.support.server.ai.api.entity.Message;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "ai")
@Data
public class AIConfiguration {

    private String baseUrl;
    private List<Message> prompts;
    private Integer limit;

}
