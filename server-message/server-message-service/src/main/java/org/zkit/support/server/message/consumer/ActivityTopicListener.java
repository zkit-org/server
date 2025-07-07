package org.zkit.support.server.message.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.zkit.support.server.message.activity.service.ActivityService;
import org.zkit.support.server.message.api.entity.request.ActivityRequest;

import com.alibaba.fastjson2.JSON;

import jakarta.annotation.Resource;

import java.util.List;

@Component
@Slf4j
public class ActivityTopicListener {

    @Resource
    private ActivityService activityService;

    @KafkaListener(topics = {"${message.activity-topic}"}, containerFactory = "batchFactory")
    public void batchConsumer(List<ConsumerRecord<String, String>> consumerRecords) {
        consumerRecords.forEach(record -> {
            try {
                log.info("ActivityTopicListener: {}", record.value());
                activityService.save(JSON.parseObject(record.value(), ActivityRequest.class));
            } catch (Exception e) {
                log.error("ActivityTopicListener error: {}", e.getMessage(), e);
            }
        });
    }

}
