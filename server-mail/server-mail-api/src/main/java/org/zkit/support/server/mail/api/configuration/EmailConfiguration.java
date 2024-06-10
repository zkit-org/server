package org.zkit.support.server.mail.api.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "email")
@Data
public class EmailConfiguration {

    private String code;
    private Boolean debug;

}
