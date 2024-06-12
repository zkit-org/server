package org.zkit.support.server.mail.api;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.zkit.support.server.mail.api.entity.EmailCode;
import org.zkit.support.server.mail.api.entity.MailSendRequest;
import org.zkit.support.starter.boot.code.PublicCode;
import org.zkit.support.server.mail.api.configuration.EmailConfiguration;
import org.zkit.support.starter.boot.exception.ResultException;
import org.zkit.support.starter.boot.utils.MessageUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class EmailCodeService {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    @Resource
    private EmailConfiguration configuration;
    @Resource
    private ServerMailApi emailService;

    private EmailCode newCode(String email, String action) {
        String codeKey = "email:code:" + action + ":" + email;
        EmailCode code = new EmailCode();
        code.setCode(configuration.getDebug() ? configuration.getCode() : String.valueOf((int)((Math.random() * 9 + 1) * 100000)));
        code.setCreatedAt(new Date());
        redisTemplate.opsForValue().set(codeKey, code, 5, TimeUnit.MINUTES);
        return code;
    }

    public void send(String email, String action) {
        String codeKey = "email:code:" + action + ":" + email;
        String timeKey = "email:code:" + action + ":" + email + ":send:time";
        EmailCode code = (EmailCode) redisTemplate.opsForValue().get(codeKey);
        if (code != null) {
            long now = System.currentTimeMillis();
            Long lastTime = (Long)redisTemplate.opsForValue().get(timeKey);
            long offset = now - code.getCreatedAt().getTime();
            if (lastTime != null && now - lastTime < 60 * 1000)
                throw new ResultException(PublicCode.THROTTLE.code, MessageUtils.get(PublicCode.THROTTLE.key));
            if (offset < 60 * 1000)
                code = this.newCode(email, action);
        }else{
            code = this.newCode(email, action);
        }
        redisTemplate.opsForValue().set(timeKey, System.currentTimeMillis(), 1, TimeUnit.MINUTES);
        if(configuration.getDebug()) {
            return;
        }
        MailSendRequest request = new MailSendRequest();
        request.setTo(email);
        request.setTemplate("email/code");
        request.setLanguage(MessageUtils.getLocale());
        Map<String, Object> map = new HashMap<>();
        map.put("code", code.getCode());
        request.setData(map);
        log.info("send email code: {}", request);
        emailService.send(request);
    }

    public boolean check(String email, String code, String action) {
        String codeKey = "email:code:" + action + ":" + email;
        EmailCode emailCode = (EmailCode) redisTemplate.opsForValue().get(codeKey);
        return !(emailCode == null || !emailCode.getCode().equals(code));
    }

}
