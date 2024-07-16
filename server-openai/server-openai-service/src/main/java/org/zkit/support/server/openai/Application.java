package org.zkit.support.server.openai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching(mode = AdviceMode.ASPECTJ)
@EnableAspectJAutoProxy(exposeProxy=true)
public class Application {

    public static void main(String[] args) {
        System.setProperty("http.proxyType", "4");
        System.setProperty("http.proxyPort", System.getenv("PROXY_PORT"));
        System.setProperty("http.proxyHost", System.getenv("PROXY_HOST"));
        System.setProperty("http.nonProxyHosts", "localhost|127.0.0.1|easykit|nacos");
        System.setProperty("http.proxySet", "true");
        SpringApplication.run(Application.class, args);
    }

}
