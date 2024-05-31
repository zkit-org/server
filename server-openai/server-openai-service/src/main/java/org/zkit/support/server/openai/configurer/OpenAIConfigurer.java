package org.zkit.support.server.openai.configurer;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.zkit.support.server.openai.configuration.OpenAIConfiguration;

@Configuration
@Slf4j
public class OpenAIConfigurer {

    @Resource
    private OpenAIConfiguration configuration;

    @Bean
    @Qualifier("openAIRestTemplate")
    public RestTemplate openaiRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + configuration.getToken());
            return execution.execute(request, body);
        });
        return restTemplate;
    }

}
