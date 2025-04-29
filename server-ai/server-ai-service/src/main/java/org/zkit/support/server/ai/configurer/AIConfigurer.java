package org.zkit.support.server.ai.configurer;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.zkit.support.server.ai.model.chat.ArkChatModel;
import org.zkit.support.server.ai.model.chat.ArkChatModelOptions;
import org.zkit.support.server.ai.model.embedding.ArkEmbeddingModel;
import org.zkit.support.server.ai.model.embedding.ArkEmbeddingModelOptions;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@RefreshScope
public class AIConfigurer {

    @Value("${spring.ai.openai.base-url:}")
    private String baseUrl;

    @Value("${spring.ai.openai.api-key:}")
    private String apiKey;

    @Value("${spring.ai.openai.chat.options.model:'}")
    private String chatModel;

    @Value("${spring.ai.openai.embedding.options.model:}")
    private String embeddingModel;

    @Value("${spring.ai.openai.chat.options.temperature:0.7}")
    private double temperature;

    @Bean
    @Primary
    public ChatModel arkChatModel() {
        ArkChatModelOptions options = ArkChatModelOptions.builder()
            .baseUrl(baseUrl)
            .apiKey(apiKey)
            .model(chatModel)
            .temperature(temperature)
            .build();
        return new ArkChatModel(options);
    }

    @Bean
    @Primary
    public EmbeddingModel arkEmbeddingModel() {
        ArkEmbeddingModelOptions options = ArkEmbeddingModelOptions.builder()
            .baseUrl(baseUrl)
            .apiKey(apiKey)
            .model(embeddingModel)
            .build();
        return new ArkEmbeddingModel(options);
    }

    @Bean
    public ChatClient chatClient(
        ChatModel model,
        VectorStore vectorStore
    ) {
        var qaAdvisor = new QuestionAnswerAdvisor(
            vectorStore, 
            SearchRequest.builder().topK(10).build(),
            """
            Context information is below.
            ---------------------
            {question_answer_context}
            ---------------------
            """
        );
        return ChatClient
            .builder(model)
            .defaultAdvisors(
                qaAdvisor
            )
            .build();
    }

}
