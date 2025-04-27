package org.zkit.support.server.ai.model.embedding;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;

public class ArkEmbeddingModel implements EmbeddingModel {

    @Override
    public EmbeddingResponse call(EmbeddingRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'call'");
    }

    @Override
    public float[] embed(Document document) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'embed'");
    }
}
