package org.zkit.support.server.message.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "alidm")
@Data
public class AliDMConfiguration {

    private String regionId;
    private String accountName;
    private String accessKeyId;
    private String accessKeySecret;

}
