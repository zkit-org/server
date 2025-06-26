package org.zkit.support.server.ai.api;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.zkit.support.server.ai.api.entity.InvokeRequest;
import org.zkit.support.server.ai.api.service.ChatApiService;
import org.zkit.support.server.ai.configuration.AIConfiguration;

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
    private VectorStore vectorStore;

    @Override
    public String invoke(InvokeRequest request) {
        UserMessage userMessage = new UserMessage(request.getMessage());
        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(aiConfiguration.getInvokeTemplate());
        
        String reference = "";
        if(request.getUseContext()) {
            SearchRequest.Builder searchRequestBuilder = SearchRequest.builder();
            searchRequestBuilder.query(request.getMessage());
            searchRequestBuilder.topK(request.getTopK() == null ? 10 : request.getTopK());
            if (request.getMetadata() != null) {
                List<String> conditions = new ArrayList<>();
                request.getMetadata().forEach((key, value) -> {
                    conditions.add(key + " == '" + value + "'");
                });
                String combined = String.join(" && ", conditions);
                searchRequestBuilder.filterExpression(combined);
            }

            List<Document> results = vectorStore.similaritySearch(searchRequestBuilder.build());
            reference = results.stream().map(Document::getFormattedContent).collect(Collectors.joining("\n"));
        }
        
        String rules = request.getRules().stream().collect(Collectors.joining("\n"));
        Message systemMessage = systemPromptTemplate.createMessage(Map.of("rules", rules, "reference", reference));

        Prompt prompt = new Prompt(List.of(userMessage, systemMessage));

        log.info("prompt: {}", prompt);

        return chatClient.prompt(prompt).call().content();
    }
}
