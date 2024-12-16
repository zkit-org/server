package org.zkit.support.server.account.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "account")
@Data
public class AccountConfiguration {

    private String su;
    private String defaultRole;
    private String initPassword;
    private String transportPrivateKey;
    private String aesKey;
    private boolean debug;
    private String emailCode;
    private String otpCode;
    private String resetOtpCode;
    private Long expiresIn;

}
