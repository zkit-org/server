package org.zkit.support.server.ai.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@RefreshScope
@Data
public class RedisConfiguration {

    @Value("${spring.data.redis.url:}")
    private String url;

}