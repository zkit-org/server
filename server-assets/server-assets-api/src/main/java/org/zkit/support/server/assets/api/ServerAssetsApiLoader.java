package org.zkit.support.server.assets.api;

import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackageClasses = ServerAssetsApiLoader.class)
public class ServerAssetsApiLoader {
}
