package org.zkit.support.server.message.api.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.zkit.support.server.message.api.configuration.MessageConfiguration;
import org.zkit.support.server.message.api.entity.request.ActivityRequest;

import com.alibaba.fastjson2.JSONObject;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ActivityService {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;
    @Resource
    private MessageConfiguration configuration;

    public void log(ActivityRequest request) {
        kafkaTemplate.send(configuration.getActivityTopic(), JSONObject.toJSONString(request));
    }

}
