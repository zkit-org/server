package org.zkit.support.server.ai.api;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.data.redis.core.RedisTemplate;
import org.zkit.support.server.ai.api.entity.InvokeRequest;
import org.zkit.support.server.ai.api.service.ChatApiService;
import org.zkit.support.server.ai.configuration.AIConfiguration;
import org.zkit.support.server.ai.vector.service.VectorStoreService;
import org.zkit.support.starter.boot.utils.MD5Utils;

import com.alibaba.fastjson2.JSON;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@DubboService
@Slf4j
public class ChatApiServiceImpl implements ChatApiService {

    @Resource
    private ChatClient chatClient;
    @Resource
    private AIConfiguration aiConfiguration;
    @Resource
    private VectorStoreService vectorStoreService;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String invoke(InvokeRequest request) {
        String requestJsonString = JSON.toJSONString(request);
        String hash = MD5Utils.text(requestJsonString);

        // 1. 查询缓存
        String cacheKey = "ai:invoke:" + hash;
        String cachedResult = redisTemplate.opsForValue().get(cacheKey);
        if (cachedResult != null) {
            return cachedResult;
        }

        // 2. 正常处理
        UserMessage userMessage = new UserMessage(request.getMessage());
        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(aiConfiguration.getInvokeTemplate());
        String reference = "--";
        if(request.getUseContext()) {
            List<Document> results = vectorStoreService.search(request.getMetadata(),request.getMessage(), request.getTopK());
            reference = results.stream().map(Document::getText).collect(Collectors.joining("\n"));
        }
        String rules = request.getRules().stream().collect(Collectors.joining("\n"));
        Message systemMessage = systemPromptTemplate.createMessage(Map.of("rules", rules, "reference", reference));
        Prompt prompt = new Prompt(List.of(userMessage, systemMessage));
        String result = chatClient.prompt(prompt).call().content();

        // 3. 写入缓存，5分钟过期
        redisTemplate.opsForValue().set(cacheKey, result, 5, TimeUnit.MINUTES);

        return result;
    }

    @Override
    public String stream(InvokeRequest request) {
        return null;
    }
}
