package org.zkit.support.server.mail.api;

import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackageClasses = ServerMailApiLoader.class)
public class ServerMailApiLoader {
}
