package org.zkit.support.server.ai.api.service;

import com.alibaba.fastjson2.JSON;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import okhttp3.sse.EventSources;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.zkit.support.server.ai.api.configuration.AIConfiguration;
import org.zkit.support.server.ai.api.entity.Document;
import org.zkit.support.server.ai.api.entity.InvokeHttpEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.zkit.support.server.ai.api.entity.InvokeRequest;
import org.zkit.support.server.ai.api.entity.Message;
import org.zkit.support.starter.boot.entity.Result;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class AIAPIService {

    @Resource
    private AIConfiguration configuration;
    @Resource
    private RestTemplate restTemplate;

    public SseEmitter stream(InvokeRequest invokeRequest) {
        SseEmitter emitter = new SseEmitter();
        InvokeHttpEntity httpEntity = getInvokeHttpEntity(invokeRequest);
        log.info("stream request: {}", JSON.toJSONString(invokeRequest));
        // 创建请求体
        okhttp3.MediaType json = okhttp3.MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(JSON.toJSONString(httpEntity), json);

        // 创建请求对象
        Request request = new Request.Builder()
                .url(configuration.getBaseUrl() + "/chain/stream")
                .post(requestBody) // 请求体
                .addHeader("Accept", "text/event-stream")
                .build();

        // 开启 Http 客户端
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)   // 建立连接的超时时间
                .readTimeout(10, TimeUnit.MINUTES)  // 建立连接后读取数据的超时时间
                .build();

        EventSource.Factory factory = EventSources.createFactory(client);
        EventSourceListener eventSourceListener = new EventSourceListener() {
            @Override
            public void onOpen(@NotNull final EventSource eventSource, @NotNull final Response response) {
                log.info("EventSourceListener onOpen");
            }

            @Override
            public void onEvent(@NotNull final EventSource eventSource, final String id, final String type, @NotNull final String data) {
                log.info("EventSourceListener onEvent {}", data);
                try {
                    emitter.send(data); // 发送数据
                } catch (IOException e) {
                    emitter.completeWithError(e); // 发送错误
                }
            }

            @Override
            public void onClosed(@NotNull final EventSource eventSource) {
                log.info("EventSourceListener onClosed");
                emitter.complete(); // 关闭连接
            }

            @Override
            public void onFailure(@NotNull final EventSource eventSource, final Throwable t, final Response response) {
                log.error("Error... [Response：{}]...", response, t);
                emitter.completeWithError(t); // 发送错误
            }
        };
        //创建事件
        factory.newEventSource(request, eventSourceListener);

        return emitter;
    }

    private InvokeHttpEntity getInvokeHttpEntity(InvokeRequest request){
        InvokeHttpEntity httpEntity = new InvokeHttpEntity();
        httpEntity.setContent(request.getContent());
        List<Message> messages = new ArrayList<>(configuration.getPrompts());
        request.getMessages().forEach(m -> {
            Message message = new Message();
            message.setRole("user");
            message.setContent(m);
            messages.add(message);
        });
        httpEntity.setMessages(messages);
        httpEntity.setLimit(configuration.getLimit());
        httpEntity.setFilter(request.getFilter());
        return httpEntity;
    }

    public Result<String> invoke(InvokeRequest request){
        InvokeHttpEntity httpEntity = getInvokeHttpEntity(request);

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
