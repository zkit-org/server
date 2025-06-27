package org.zkit.support.server.ai.api;

import org.zkit.support.server.ai.api.service.VectorStoreApiService;
import org.zkit.support.server.ai.vector.service.VectorStoreService;

import jakarta.annotation.Resource;

import java.util.List;
import java.util.Map;

import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class VectorStoreApiServiceImpl implements VectorStoreApiService {

    @Resource
    private VectorStoreService vectorStoreService;

    @Override
    public void add(org.zkit.support.server.ai.api.entity.Document document) {
        vectorStoreService.add(List.of(document));
    }

    @Override
    public void add(List<org.zkit.support.server.ai.api.entity.Document> documents) {
        vectorStoreService.add(documents);
    }

    @Override
    public void delete(Map<String, String> metadata) {
        vectorStoreService.delete(metadata);
    }
}
