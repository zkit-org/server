package org.zkit.support.server.ai.model.embedding;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArkEmbeddingModelRequest {
    private String model;
    private List<String> input;
} 