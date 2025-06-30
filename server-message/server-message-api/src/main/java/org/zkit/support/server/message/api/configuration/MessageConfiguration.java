package org.zkit.support.server.message.api.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "message")
@Data
public class MessageConfiguration {

    private String mailTopic;
    private String activeTopic;

}
