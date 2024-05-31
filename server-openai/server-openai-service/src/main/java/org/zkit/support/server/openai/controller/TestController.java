package org.zkit.support.server.openai.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.zkit.support.server.openai.configuration.OpenAIConfiguration;
import org.zkit.support.server.openai.entity.ChatRequest;
import org.zkit.support.server.openai.entity.ChatResponse;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Resource
    @Qualifier("openAIRestTemplate")
    private RestTemplate restTemplate;
    @Resource
    private OpenAIConfiguration configuration;

    @GetMapping("/chat")
    public String chat(@RequestParam String prompt) {
        // create a request
        ChatRequest request = new ChatRequest(configuration.getModel(), prompt);
        log.info("request {}", request);
        // call the API
        ChatResponse response = restTemplate.postForObject(configuration.getApiUrl(), request, ChatResponse.class);
        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            return "No response";
        }
        log.info("response {}", response);
        // return the first response
        return response.getChoices().get(0).getMessage().getContent();
    }

}
