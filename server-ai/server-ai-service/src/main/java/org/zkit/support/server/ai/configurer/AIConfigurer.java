package org.zkit.support.server.ai.configurer;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.TokenCountBatchingStrategy;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.redis.RedisVectorStore;
import org.springframework.ai.vectorstore.redis.RedisVectorStore.MetadataField;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.zkit.support.server.ai.configuration.OpenAIConfiguration;
import org.zkit.support.server.ai.configuration.RedisConfiguration;
import org.zkit.support.server.ai.configuration.VectorStoreConfiguration;
import org.zkit.support.server.ai.model.chat.ArkChatModel;
import org.zkit.support.server.ai.model.chat.ArkChatModelOptions;
import org.zkit.support.server.ai.model.embedding.ArkEmbeddingModel;
import org.zkit.support.server.ai.model.embedding.ArkEmbeddingModelOptions;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.JedisPooled;

@Configuration
@Slf4j
public class AIConfigurer {

    @Resource
    private RedisConfiguration redisConfiguration;

    @Resource
    private OpenAIConfiguration openAIConfiguration;

    @Resource
    private VectorStoreConfiguration vectorStoreConfiguration;

    @Bean
    public JedisPooled jedisPooled() {
        return new JedisPooled(redisConfiguration.getUrl());
    }

    @Bean
    public VectorStore vectorStore(JedisPooled jedisPooled, EmbeddingModel embeddingModel) {
        return RedisVectorStore.builder(jedisPooled, embeddingModel)
                .indexName(vectorStoreConfiguration.getIndexName())
                .prefix(vectorStoreConfiguration.getPrefix())
                .initializeSchema(vectorStoreConfiguration.isInitializeSchema())
                .batchingStrategy(new TokenCountBatchingStrategy())
                .metadataFields(List.of(
                    MetadataField.text("source"),
                    MetadataField.text("language")
                ))
                .build();
    }

    @Bean
    @Primary
    public ChatModel arkChatModel() {
        ArkChatModelOptions options = ArkChatModelOptions.builder()
            .baseUrl(openAIConfiguration.getBaseUrl())
            .apiKey(openAIConfiguration.getApiKey())
            .model(openAIConfiguration.getChatModel())
            .temperature(openAIConfiguration.getTemperature())
            .build();
        return new ArkChatModel(options);
    }

    @Bean
    @Primary
    public EmbeddingModel arkEmbeddingModel() {
        ArkEmbeddingModelOptions options = ArkEmbeddingModelOptions.builder()
            .baseUrl(openAIConfiguration.getBaseUrl())
            .apiKey(openAIConfiguration.getApiKey())
            .model(openAIConfiguration.getEmbeddingModel())
            .build();
        return new ArkEmbeddingModel(options);
    }

    @Bean
    public ChatClient chatClient(
        ChatModel model,
        VectorStore vectorStore
    ) {
        var qaAdvisor = QuestionAnswerAdvisor.builder(vectorStore)
            .searchRequest(SearchRequest.builder().topK(10).build())
            .promptTemplate(new PromptTemplate("""
                Context information is below.
                ---------------------
                {question_answer_context}
                ---------------------
                """))
            .build();
        return ChatClient
            .builder(model)
            .defaultAdvisors(qaAdvisor)
            .build();
    }

}
