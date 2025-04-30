package org.zkit.support.server.ai.model.chat;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;

@Slf4j
public class ArkChatModel implements ChatModel {

    private ArkChatModelOptions options;
    private WebClient webClient;

    public ArkChatModel(ArkChatModelOptions options) {
        this.options = options;
        this.webClient = WebClient.builder()
            .baseUrl(options.getBaseUrl())
            .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + options.getApiKey())
            .build();
    }

    @Override
    public ChatResponse call(Prompt prompt) {
        log.info("prompt: " + prompt);
        // 提取 Spring AI 的消息为 API 兼容格式
        List<ArkChatModelMessage> requestMessages = prompt.getInstructions().stream()
                .map(m -> new ArkChatModelMessage(toRole(m), m.getText()))
                .collect(Collectors.toList());

        ArkChatModelRequest request = ArkChatModelRequest.builder()
            .model(options.getModel())
            .messages(requestMessages)
            .temperature(options.getTemperature())
            .build();

        ArkChatModelResponse response = webClient.post()
                .uri("/chat/completions")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ArkChatModelResponse.class)
                .block();

        String output = response.getChoices().get(0).getMessage().getContent();
        AssistantMessage assistantMessage = new AssistantMessage(output);
        return new ChatResponse(List.of(new Generation(assistantMessage)));
    }

    private String toRole(Message m) {
        switch (m.getMessageType()) {
            case USER -> { return "user"; }
            case SYSTEM -> { return "system"; }
            case ASSISTANT -> { return "assistant"; }
            default -> throw new IllegalArgumentException("Unknown role");
        }
    }
    
}
