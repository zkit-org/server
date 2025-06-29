package org.zkit.support.server.message.service;

import com.alibaba.fastjson2.JSONObject;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zkit.support.server.message.api.entity.request.SendMailRequest;

@Service
@Slf4j
public class MailService {

    @Resource
    private AliDMService aliDMService;

    public void sendMail(String jsonData) {
        SendMailRequest request = JSONObject.parseObject(jsonData, SendMailRequest.class);
        log.info("send mail: {}", request);
        aliDMService.singleSendMail(request);
    }

}
