package org.zkit.support.server.message.consumer;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.zkit.support.server.message.service.MailService;

import java.util.List;

@Component
@Slf4j
public class MailTopicListener {

    @Resource
    private MailService mailService;

    @KafkaListener(topics = {"${message.mail-topic}"}, containerFactory = "batchFactory")
    public void batchConsumer(List<ConsumerRecord<?, ?>> consumerRecords, Acknowledgment ack) {
        consumerRecords.forEach(record -> {
            mailService.sendMail(record.value().toString());
        });
        ack.acknowledge();
    }

}
