package org.zkit.support.server.ai.model.embedding;

import lombok.Data;

@Data
public class ArkEmbeddingModelOptions {
    private String baseUrl;
    private String apiKey;
    private String model;
} 