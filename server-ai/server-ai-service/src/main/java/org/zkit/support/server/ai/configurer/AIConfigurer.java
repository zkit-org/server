package org.zkit.support.server.ai.configurer;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.zkit.support.server.ai.model.chat.ArkChatModel;
import org.zkit.support.server.ai.model.chat.ArkChatModelOptions;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class AIConfigurer {

    @Value("${spring.ai.openai.base-url}")
    private String baseUrl;

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    @Value("${spring.ai.openai.chat.options.model}")
    private String model;

    @Value("${spring.ai.openai.chat.options.temperature:0.7}")
    private double temperature;

    @Bean
    @Primary
    public ChatModel arkModel() {
        ArkChatModelOptions options = ArkChatModelOptions.builder()
            .baseUrl(baseUrl)
            .apiKey(apiKey)
            .model(model)
            .temperature(temperature)
            .build();
        log.info("options: " + options);
        return new ArkChatModel(options);
    }

    @Bean
    public ChatClient chatClient(ChatModel model) {
        return ChatClient.builder(model).build();
    }

}
