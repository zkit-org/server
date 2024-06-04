package org.zkit.support.server.openai.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "translator")
@Data
public class TranslatorConfiguration {

    private List<String> prompt;
    private Long expiration;

}
