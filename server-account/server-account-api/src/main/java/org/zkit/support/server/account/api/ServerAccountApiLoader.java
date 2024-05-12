package org.zkit.support.server.account.api;

import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackageClasses = ServerAccountApiLoader.class)
public class ServerAccountApiLoader {
}
