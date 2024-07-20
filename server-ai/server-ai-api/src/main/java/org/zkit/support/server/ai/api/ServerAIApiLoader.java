package org.zkit.support.server.ai.api;

import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackageClasses = ServerAIApiLoader.class)
public class ServerAIApiLoader {
}
