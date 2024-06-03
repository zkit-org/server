package org.zkit.support.server.openai.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.stereotype.Service;
import org.zkit.support.server.openai.api.entity.TranslatorRequest;

import java.util.List;
import java.util.stream.Stream;

@Service
@Slf4j
public class TranslatorService {

    private final ChatClient chatClient;

    TranslatorService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public List<String> translate(TranslatorRequest request){
        List<Message> messages = List.of(
                new SystemMessage("你是一个翻译助手。"),
                new SystemMessage("请给出多条翻译结果，使用,分割，最多10条；最符合的排序靠前；词性尽可能保持一致；中文繁体简体取一条。"),
                new SystemMessage("请翻译成 "+request.getTarget()+"，使用 "+request.getTarget()+" 回复结果，且只需要回复翻译结果。")
        );
        String result = chatClient.prompt().messages(messages).user(request.getMessage()).call().content();
        log.info("origin: {}", result);
        return Stream.of(result.split(",")).map(String::trim).distinct().toList();
    }
}
