package org.zkit.support.server.assets.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "oss")
@Data
public class OSSConfiguration {

    private String provider;
    private String prefix;

}
