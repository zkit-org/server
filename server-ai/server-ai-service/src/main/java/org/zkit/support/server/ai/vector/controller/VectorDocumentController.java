package org.zkit.support.server.ai.vector.controller;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zkit.support.starter.security.annotation.PublicRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/vector/document")
@Slf4j
@Tag(name = "VectorDocumentController", description = "文本向量")
public class VectorDocumentController {

    @Resource
    private VectorStore vectorStore;

    @GetMapping("/add")
    @PublicRequest
    @Operation(summary = "添加")
    public void add(@RequestParam(value = "message", defaultValue = "Spring AI is?") String message) {
        vectorStore.add(List.of(new Document(message)));
    }
}
