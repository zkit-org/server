package org.zkit.support.server.ai.model.embedding;

import java.util.List;

import lombok.Data;

@Data
public class ArkEmbeddingModelResponse {
    private Long created;
    private String id;
    private List<EmbeddingData> data;
    private String model;
    private String object;
    private Usage usage;
} 

@Data
class EmbeddingData {
    private List<Float> embedding;
    private Integer index;
    private String object;
} 

@Data
class Usage {
    private Integer prompt_tokens;
    private Integer total_tokens;
}
