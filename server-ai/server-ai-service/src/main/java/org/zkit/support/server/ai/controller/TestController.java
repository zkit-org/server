package org.zkit.support.server.ai.controller;

import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zkit.support.starter.security.annotation.PublicRequest;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Resource
    private ChatClient chatClient;

    @Resource
    private VectorStore vectorStore;

    @GetMapping("/generate")
    @PublicRequest
    public Map<String, String> generate(
            @RequestParam(value = "message", defaultValue = "Spring AI is?") String message) {
        return Map.of("generation", chatClient.prompt(message).call().content());
    }

    @GetMapping("/vector/search")
    @PublicRequest
    public List<Document> vector() {
        List<Document> results = vectorStore.similaritySearch(SearchRequest.builder().query("Big").topK(5).build());
        log.info("results: " + results);
        return results;
    }
    
}
