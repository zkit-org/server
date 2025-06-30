package org.zkit.support.server.message.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "mail")
@Data
public class EmailConfiguration {

    private String code;
    private Boolean debug = false;

}
