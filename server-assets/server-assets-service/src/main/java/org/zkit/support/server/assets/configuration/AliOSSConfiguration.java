package org.zkit.support.server.assets.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "alioss")
@Data
public class AliOSSConfiguration {

    private String endpoint;
    private String keyId;
    private String keySecret;
    private String bucket;

}
