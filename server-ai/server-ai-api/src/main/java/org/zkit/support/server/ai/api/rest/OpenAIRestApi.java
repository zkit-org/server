package org.zkit.support.server.ai.api.rest;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zkit.support.server.ai.api.entity.TranslatorRequest;
import org.zkit.support.server.ai.api.configuration.OpenAIConfiguration;
import org.zkit.support.starter.boot.entity.Result;
import org.zkit.support.starter.boot.okhttp.HTTPService;

import java.util.List;

@Service
@Slf4j
public class OpenAIRestApi {

    @Resource
    private OpenAIConfiguration configuration;
    @Resource
    private HTTPService httpService;

    public List<String> translator(TranslatorRequest data) {
        try{
            String body = httpService.post(configuration.getBaseUrl()+"/api/openai/translator", JSON.toJSONString(data));
            Result<List<String>> result = JSON.parseObject(body, new TypeReference<Result<List<String>>>(){});
            log.info("result: {}", result);
            if (result != null && result.getData() != null) {
                return result.getData();
            }
        }catch (Exception e) {
            log.error(e.getMessage());
        }
        return List.of();
    }

}
