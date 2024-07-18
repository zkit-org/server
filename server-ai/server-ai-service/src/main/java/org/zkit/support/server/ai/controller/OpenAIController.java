package org.zkit.support.server.ai.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.zkit.support.server.ai.service.TranslatorService;
import org.zkit.support.server.ai.api.entity.TranslatorRequest;

import java.util.List;

@RestController
@RequestMapping("/api/openai")
@Slf4j
public class OpenAIController {

    @Resource
    private TranslatorService translatorService;

    @PostMapping("/translator")
    public List<String> translator(@RequestBody TranslatorRequest request) {
        return translatorService.translate(request);
    }

}
