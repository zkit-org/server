package org.zkit.support.server.openai.api.rest;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;
import org.zkit.support.server.openai.api.configuration.OpenAIConfiguration;
import org.zkit.support.server.openai.api.entity.TranslatorRequest;
import org.zkit.support.starter.boot.entity.Result;

import java.util.List;

@Service
@Slf4j
public class OpenAIRestApi {

    @Resource
    private OpenAIConfiguration configuration;
    @Resource
    private OkHttpClient client;

    public List<String> translator(TranslatorRequest data) {
        RequestBody requestBody = RequestBody.create(JSON.toJSONString(data), MediaType.parse("application/json; charset=utf-8"));
        Request request = new Request.Builder().url(configuration.getBaseUrl()+"/api/openai/translator").post(requestBody).build();
        try{
            try (Response response = client.newCall(request).execute()) {
                if (response.body() != null) {
                    String body = response.body().string();
                    Result<List<String>> result = JSON.parseObject(body, new TypeReference<Result<List<String>>>(){});
                    log.info("result: {}", result);
                    if (result != null && result.getData() != null) {
                        return result.getData();
                    }
                }
            }
        }catch (Exception e) {
            log.error(e.getMessage());
        }
        return List.of();
    }

}
