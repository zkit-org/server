package org.zkit.support.server.ai.vector.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.zkit.support.server.ai.vector.service.VectorStoreService;

import jakarta.annotation.Resource;

@Service
public class VectorStoreServiceImpl implements VectorStoreService {

    @Resource
    private VectorStore vectorStore;

    @Override
    public void add(List<org.zkit.support.server.ai.api.entity.Document> documents) {
        vectorStore.add(documents.stream().map(document -> new Document(document.getContent(), document.getMetadata().entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))))
                .toList());
    }

    @Override
    public void delete(Map<String, String> metadata) {
        vectorStore.delete(getFilterExpression(metadata));
    }

    private String getFilterExpression(Map<String, String> metadata) {
        List<String> conditions = new ArrayList<>();
        metadata.forEach((key, value) -> {
            conditions.add(key + " == '" + value + "'");
        });
        String combined = String.join(" && ", conditions);
        return combined;
    }

    private SearchRequest toSearchRequest(Map<String, String> metadata, String query, Integer topK) {
        SearchRequest.Builder searchRequestBuilder = SearchRequest.builder();
        searchRequestBuilder.topK(topK == null ? 10 : topK);
        if (query != null) {
            searchRequestBuilder.query(query);
        }
        if (metadata != null) {
            searchRequestBuilder.filterExpression(getFilterExpression(metadata));
        }
        return searchRequestBuilder.build();
    }

    @Override
    public List<Document> search(Map<String, String> metadata, String query, Integer topK) {
        return vectorStore.similaritySearch(toSearchRequest(metadata, query, topK));
    }

    @Override
    public List<Document> search(Map<String, String> metadata, Integer topK) {
        return search(metadata, null, topK);
    }

}
