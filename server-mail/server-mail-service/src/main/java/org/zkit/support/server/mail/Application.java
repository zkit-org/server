package org.zkit.support.server.mail;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("org.zkit.support.server.mail.*.mapper")
@EnableDiscoveryClient
@EnableCaching(mode = AdviceMode.ASPECTJ)
@EnableAspectJAutoProxy(exposeProxy=true)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}