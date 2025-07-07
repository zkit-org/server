package org.zkit.support.server.message.api.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.zkit.support.server.message.api.configuration.MessageConfiguration;
import org.zkit.support.server.message.api.entity.request.SendMailRequest;

import com.alibaba.fastjson2.JSON;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Component("apiMailService")
@Slf4j
public class MailService {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;
    @Resource
    private MessageConfiguration configuration;

    public void send(SendMailRequest request) {
        kafkaTemplate.send(configuration.getMailTopic(), JSON.toJSONString(request));
    }

}
