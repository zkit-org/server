package org.zkit.support.server.ai.api.service;

import java.util.List;

import org.zkit.support.server.ai.api.entity.Document;

public interface VectorStoreApiService {

    void add(Document document);
    void add(List<Document> documents);

}
