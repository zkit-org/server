package org.zkit.support.server.openai.configurer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.zkit.support.server.openai.service.MockWeatherService;

import java.util.function.Function;

@Configuration
@Slf4j
public class OpenAIConfigurer {

    @Bean
    @Description("Get the weather in location") // function description
    public Function<MockWeatherService.Request, MockWeatherService.Response> weatherFunction() {
        return new MockWeatherService();
    }

}
