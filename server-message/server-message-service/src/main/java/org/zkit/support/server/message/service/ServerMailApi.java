package org.zkit.support.server.message.service;

import com.alibaba.fastjson2.JSONObject;
import jakarta.annotation.Resource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.zkit.support.server.message.configuration.EmailConfiguration;
import org.zkit.support.server.message.api.entity.request.SendMailRequest;

@Component
public class ServerMailApi {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;
    @Resource
    private EmailConfiguration configuration;

    public void send(SendMailRequest request) {
        kafkaTemplate.send(configuration.getTopic(), JSONObject.toJSONString(request));
    }

}
