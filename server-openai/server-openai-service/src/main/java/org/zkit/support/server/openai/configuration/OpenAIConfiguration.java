package org.zkit.support.server.openai.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "openai")
@Data
public class OpenAIConfiguration {

    private String token;
    private String model;
    private String apiUrl;

}
