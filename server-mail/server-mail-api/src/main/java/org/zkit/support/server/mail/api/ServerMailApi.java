package org.zkit.support.server.mail.api;

import com.alibaba.fastjson2.JSONObject;
import jakarta.annotation.Resource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class ServerMailApi {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    public void test() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("datekey", 20210610);
        map.put("userid", 1);
        map.put("salaryAmount", 100);
        //向kafka的big_data_topic主题推送数据
        kafkaTemplate.send("mail", JSONObject.toJSONString(map));
    }

}
