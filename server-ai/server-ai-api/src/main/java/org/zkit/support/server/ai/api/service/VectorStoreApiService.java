package org.zkit.support.server.ai.api.service;

import java.util.List;
import java.util.Map;

import org.zkit.support.server.ai.api.entity.Document;

public interface VectorStoreApiService {

    void add(Document document);
    void add(List<Document> documents);
    void delete(Map<String, String> metadata);

}
