package org.zkit.support.server.ai.api;

import org.zkit.support.server.ai.api.service.VectorStoreApiService;

import jakarta.annotation.Resource;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;

@DubboService
public class VectorStoreApiServiceImpl implements VectorStoreApiService {

    @Resource
    private VectorStore vectorStore;

    @Override
    public void add(org.zkit.support.server.ai.api.entity.Document document) {
        vectorStore.add(List.of(new Document(document.getId(), document.getContent(), document.getMetadata())));
    }

    @Override
    public void add(List<org.zkit.support.server.ai.api.entity.Document> documents) {
        vectorStore.add(documents.stream().map(document -> new Document(document.getId(), document.getContent(), document.getMetadata())).toList());
    }
}
