package org.zkit.support.server.mail.internal;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zkit.support.server.mail.api.constant.MailApiRoute;
import org.zkit.support.server.mail.api.entity.request.CheckCodeRequest;
import org.zkit.support.server.mail.api.entity.request.SendCodeRequest;
import org.zkit.support.server.mail.service.EmailCodeService;

@RestController
@Slf4j
@Tag(name = "internal-mail", description = "[内部接口]邮件服务")
public class InternalMailController {

    @Resource
    private EmailCodeService emailCodeService;

    @Operation(summary = "发送邮件")
    @PostMapping(MailApiRoute.SEND)
    public void send(@RequestBody SendCodeRequest request) {
        emailCodeService.send(request.getEmail(), request.getAction());
    }

    @Operation(summary = "验证验证码")
    @PostMapping(MailApiRoute.CHECK)
    public Boolean check(@RequestBody CheckCodeRequest request) {
        return emailCodeService.check(request.getEmail(), request.getCode(), request.getAction());
    }

}
