package org.zkit.support.server.mail.api;

import com.alibaba.fastjson2.JSONObject;
import jakarta.annotation.Resource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.zkit.support.server.mail.api.entity.MailSendRequest;

@Component
public class ServerMailApi {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(MailSendRequest request) {
        kafkaTemplate.send("mail", JSONObject.toJSONString(request));
    }

}
