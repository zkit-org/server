package org.zkit.support.server.authub;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.zkit.support.starter.boot.fastjson.FastjsonAutoTypePackages;

@SpringBootApplication()
@MapperScan("org.zkit.support.server.authub.*.mapper")
@FastjsonAutoTypePackages({ "org.zkit.support" })
@EnableCaching(mode = AdviceMode.ASPECTJ)
@EnableAspectJAutoProxy(exposeProxy=true)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
