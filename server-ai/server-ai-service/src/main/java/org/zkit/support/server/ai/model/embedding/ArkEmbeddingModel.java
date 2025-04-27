package org.zkit.support.server.ai.model.embedding;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.AbstractEmbeddingModel;
import org.springframework.ai.embedding.Embedding;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.embedding.EmbeddingResponseMetadata;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.HttpHeaders;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ArkEmbeddingModel extends AbstractEmbeddingModel {

    private ArkEmbeddingModelOptions options;
    private WebClient webClient;

    public ArkEmbeddingModel(ArkEmbeddingModelOptions options) {
        this.options = options;
        this.webClient = WebClient.builder()
            .baseUrl(options.getBaseUrl())
            .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + options.getApiKey())
            .build();
    }

    @Override
    public EmbeddingResponse call(EmbeddingRequest request) {
        log.info("request: " + request);
        log.info("options: " + options);
        
        ArkEmbeddingModelRequest modelRequest = ArkEmbeddingModelRequest.builder()
            .model(options.getModel())
            .input(request.getInstructions())
            .build();

        log.info("request: " + modelRequest);
        ArkEmbeddingModelResponse response = webClient.post()
                .uri("/embeddings")
                .bodyValue(modelRequest)
                .retrieve()
                .bodyToMono(ArkEmbeddingModelResponse.class)
                .block();

        List<Double> embedding = response.getEmbedding();
        float[] floatArray = new float[embedding.size()];
        for (int i = 0; i < embedding.size(); i++) {
            floatArray[i] = embedding.get(i).floatValue();
        }

        Embedding embeddingObj = new Embedding(floatArray, 0);
        return new EmbeddingResponse(List.of(embeddingObj), new EmbeddingResponseMetadata(options.getModel(), null));
    }

    @Override
    public float[] embed(Document document) {
        EmbeddingRequest request = new EmbeddingRequest(List.of(document.getText()), null);
        EmbeddingResponse response = call(request);
        return response.getResults().get(0).getOutput();
    }
}
