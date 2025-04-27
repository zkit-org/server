package org.zkit.support.server.ai.controller;

import java.util.Map;

import org.springframework.ai.openai.OpenAiChatModel;
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
    private OpenAiChatModel chatModel;

    @GetMapping("/generate")
    @PublicRequest
    public Map<String, String> generate(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        return Map.of("generation", this.chatModel.call(message));
    }
}
