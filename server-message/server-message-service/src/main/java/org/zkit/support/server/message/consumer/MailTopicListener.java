package org.zkit.support.server.message.consumer;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.zkit.support.server.message.service.MailService;

import com.alibaba.fastjson2.JSON;

import org.zkit.support.server.message.api.entity.request.SendMailRequest;

import java.util.List;

@Component
@Slf4j
public class MailTopicListener {

    @Resource
    private MailService mailService;

    @KafkaListener(topics = {"${message.mail-topic}"}, containerFactory = "batchFactory")
    public void batchConsumer(List<ConsumerRecord<String, String>> consumerRecords) {
        consumerRecords.forEach(record -> {
            try {
                log.info("MailTopicListener: {}", record.value());
                mailService.sendMail(JSON.parseObject(record.value(), SendMailRequest.class));
            } catch (Exception e) {
                log.error("MailTopicListener error: {}", e.getMessage(), e);
            }
        });
    }

}
