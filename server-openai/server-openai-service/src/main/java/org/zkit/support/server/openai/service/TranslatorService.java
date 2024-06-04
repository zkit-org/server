package org.zkit.support.server.openai.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.zkit.support.server.openai.api.entity.TranslatorRequest;
import org.zkit.support.server.openai.configuration.TranslatorConfiguration;
import org.zkit.support.starter.boot.utils.MD5Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Service
@Slf4j
public class TranslatorService {

    private final ChatClient chatClient;
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private TranslatorConfiguration configuration;

    TranslatorService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public List<String> translate(TranslatorRequest request){
        String messageHash = MD5Utils.text(request.getMessage());
        String cacheKey = "openai:translator:" + request.getTarget() + ":" + messageHash;
        String result = redisTemplate.opsForValue().get(cacheKey);
        log.info("origin in cache: {}", result);
        if(result == null) {
            List<Message> messages = new ArrayList<>();
            configuration.getPrompt().forEach(prompt -> messages.add(new SystemMessage(prompt.replaceAll("\\{target}", request.getTarget()))));
            result = chatClient.prompt().messages(messages).user(request.getMessage()).call().content();
            log.info("origin: {}", result);
            redisTemplate.opsForValue().set(cacheKey, result, configuration.getExpiration(), TimeUnit.SECONDS);
        }
        return Stream.of(result.split(",")).map(String::trim).distinct().toList();
    }
}
