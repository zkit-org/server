package org.zkit.support.server.mail.service;

import com.alibaba.fastjson2.JSONObject;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zkit.support.server.mail.entity.MailSendRequest;

@Service
@Slf4j
public class MailService {

    @Resource
    private AliDMService aliDMService;

    public void sendMail(String jsonData) {
        MailSendRequest request = JSONObject.parseObject(jsonData, MailSendRequest.class);
        log.info("send mail: {}", request);
        aliDMService.singleSendMail(request);
    }

}
