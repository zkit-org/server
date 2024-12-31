package org.zkit.support.server.ai.api.service;

import com.alibaba.fastjson2.JSON;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.zkit.support.server.ai.api.configuration.AIConfiguration;
import org.zkit.support.server.ai.api.entity.Document;
import org.zkit.support.server.ai.api.entity.InvokeHttpEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.zkit.support.server.ai.api.entity.InvokeRequest;
import org.zkit.support.server.ai.api.entity.Message;
import org.zkit.support.starter.boot.entity.Result;

import java.util.List;

@Slf4j
@Service
public class AIAPIService {

    @Resource
    private AIConfiguration configuration;
    @Resource
    private RestTemplate restTemplate;

    public Result<String> invoke(InvokeRequest request){
        InvokeHttpEntity httpEntity = new InvokeHttpEntity();
        httpEntity.setContent(request.getContent());
        List<Message> messages = configuration.getPrompts();
        request.getMessages().forEach(m -> {
            Message message = new Message();
            message.setRole("user");
            message.setContent(m);
            messages.add(message);
        });
        httpEntity.setMessages(messages);
        httpEntity.setLimit(configuration.getLimit());
        httpEntity.setFilter(request.getFilter());

        log.info("invoke request: {}", JSON.toJSONString(httpEntity));

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);

        HttpEntity<String> entity = new HttpEntity<>(JSON.toJSONString(httpEntity), headers);
        String r = restTemplate.exchange(configuration.getBaseUrl() + "/chain/invoke", HttpMethod.POST, entity, String.class).getBody();

        log.info("invoke response: {}", r);
        return JSON.parseObject(r, new com.alibaba.fastjson2.TypeReference<Result<String>>(){});
    }

    public Result<?> addDocuments(List<Document> documents){
        log.info("addDocuments request: {}", JSON.toJSONString(documents));

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);

        HttpEntity<String> entity = new HttpEntity<>(JSON.toJSONString(documents), headers);
        String r = restTemplate.exchange(configuration.getBaseUrl() + "/vector/document/add", HttpMethod.POST, entity, String.class).getBody();

        log.info("addDocuments response: {}", r);
        return JSON.parseObject(r, new com.alibaba.fastjson2.TypeReference<Result<String>>(){});
    }

    public Result<?> removeDocuments(List<String> ids){
        log.info("removeDocuments request: {}", JSON.toJSONString(ids));

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);

        HttpEntity<String> entity = new HttpEntity<>(JSON.toJSONString(ids), headers);
        String r = restTemplate.exchange(configuration.getBaseUrl() + "/vector/document/remove", HttpMethod.DELETE, entity, String.class).getBody();

        log.info("removeDocuments response: {}", r);
        return JSON.parseObject(r, new com.alibaba.fastjson2.TypeReference<Result<String>>(){});
    }

}
