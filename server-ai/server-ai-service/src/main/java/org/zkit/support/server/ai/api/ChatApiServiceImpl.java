package org.zkit.support.server.ai.api;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.document.Document;
import org.zkit.support.server.ai.api.entity.InvokeRequest;
import org.zkit.support.server.ai.api.service.ChatApiService;
import org.zkit.support.server.ai.configuration.AIConfiguration;
import org.zkit.support.server.ai.vector.service.VectorStoreService;

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

    @Override
    public String invoke(InvokeRequest request) {
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

        return chatClient.prompt(prompt).call().content();
    }

    @Override
    public String stream(InvokeRequest request) {
        return null;
    }
}
