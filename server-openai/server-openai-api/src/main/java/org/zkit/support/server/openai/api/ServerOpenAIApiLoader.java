package org.zkit.support.server.openai.api;

import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackageClasses = ServerOpenAIApiLoader.class)
public class ServerOpenAIApiLoader {
}
