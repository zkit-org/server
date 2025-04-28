package org.zkit.support.server.ai.model.embedding;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArkEmbeddingModelOptions {
    private String baseUrl;
    private String apiKey;
    private String model;
} 