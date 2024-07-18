package org.zkit.support.server.ai.service;

import com.alibaba.fastjson2.JSON;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.zkit.support.server.ai.api.entity.TranslatorRequest;
import org.zkit.support.server.ai.configuration.AIConfiguration;
import org.zkit.support.server.ai.configuration.TranslatorConfiguration;
import org.zkit.support.server.ai.entity.ChatMessage;
import org.zkit.support.server.ai.entity.ChatRequest;
import org.zkit.support.server.ai.entity.ChatResponse;
import org.zkit.support.starter.boot.utils.MD5Utils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Service
@Slf4j
public class TranslatorService {

    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private TranslatorConfiguration translatorConfiguration;
    @Resource
    private AIConfiguration aiConfiguration;
    @Resource
    private RestTemplate restTemplate;


    public List<String> translate(TranslatorRequest request){
        String messageHash = MD5Utils.text(request.getMessage());
        String cacheKey = "openai:translator:" + request.getTarget() + ":" + messageHash;
        String result = redisTemplate.opsForValue().get(cacheKey);
        log.info("origin in cache: {}", result);
        if(result == null) {
            List<ChatMessage> messages = new ArrayList<>();
            translatorConfiguration.getPrompt().forEach(prompt -> messages.add(new ChatMessage("system", prompt.replaceAll("\\{target}", request.getTarget()))));
            messages.add(new ChatMessage("user", request.getMessage()));

            ChatRequest chatRequest = new ChatRequest();
            chatRequest.setModel(aiConfiguration.getModel());
            chatRequest.setMessages(messages);
            chatRequest.setTemperature(0.3);

            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
            headers.setContentType(type);
            headers.setBearerAuth(aiConfiguration.getApiKey());

            HttpEntity<String> entity = new HttpEntity<>(JSON.toJSONString(chatRequest), headers);
            String restResult = restTemplate.postForObject(aiConfiguration.getHost() + aiConfiguration.getChatApi(), entity, String.class);
            ChatResponse response = JSON.parseObject(restResult, ChatResponse.class);
            log.info("origin: {}", response);
            if (response != null) {
                result = response.getChoices().get(0).getMessage().getContent();
                redisTemplate.opsForValue().set(cacheKey, result, translatorConfiguration.getExpiration(), TimeUnit.SECONDS);
            }else{
                result = "";
            }
        }
        return Stream.of(result.split(",")).map(String::trim).distinct().toList();
    }
}
