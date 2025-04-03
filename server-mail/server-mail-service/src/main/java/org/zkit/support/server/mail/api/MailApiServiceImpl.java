package org.zkit.support.server.mail.api;

import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboService;
import org.zkit.support.server.mail.api.entity.request.CheckCodeRequest;
import org.zkit.support.server.mail.api.entity.request.SendCodeRequest;
import org.zkit.support.server.mail.api.entity.request.SendMailRequest;
import org.zkit.support.server.mail.api.service.MailApiService;
import org.zkit.support.server.mail.service.EmailCodeService;
import org.zkit.support.server.mail.service.ServerMailApi;

@DubboService
public class MailApiServiceImpl implements MailApiService {

    @Resource
    private EmailCodeService emailCodeService;
    @Resource
    private ServerMailApi serverMailApi;

    @Override
    public void send(SendMailRequest request) {
        serverMailApi.send(request);
    }

    @Override
    public void sendCode(SendCodeRequest request) {
        emailCodeService.send(request.getEmail(), request.getAction());
    }

    @Override
    public Boolean check(CheckCodeRequest request) {
        return emailCodeService.check(request.getEmail(), request.getCode(), request.getAction());
    }
}
