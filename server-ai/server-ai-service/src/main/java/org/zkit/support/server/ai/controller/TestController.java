package org.zkit.support.server.ai.controller;

import java.util.List;
import java.util.Map;

import org.checkerframework.checker.units.qual.m;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.document.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zkit.support.starter.security.annotation.PublicRequest;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/ai")
public class TestController {

    @Resource
    private ChatModel chatModel;

    @Resource
    private EmbeddingModel embeddingModel;

    @GetMapping("/generate")
    @PublicRequest
    public Map<String, String> generate(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        return Map.of("generation", chatModel.call(message));
    }

    @GetMapping("/embed")
    @PublicRequest
    public void embed(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        embeddingModel.embed(List.of(message));
    }
}
