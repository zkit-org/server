package org.zkit.support.server.ai.vector.service;

import java.util.List;
import java.util.Map;

import org.springframework.ai.document.Document;

public interface VectorStoreService {

    void add(List<org.zkit.support.server.ai.api.entity.Document> documents);
    void delete(Map<String, String> metadata); 
    List<Document> search(Map<String, String> metadata, String query, Integer topK);
    List<Document> search(Map<String, String> metadata, Integer topK);
}
