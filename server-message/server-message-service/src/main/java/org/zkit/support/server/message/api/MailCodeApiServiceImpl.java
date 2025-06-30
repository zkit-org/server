package org.zkit.support.server.message.api;

import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboService;
import org.zkit.support.server.message.api.entity.request.CheckCodeRequest;
import org.zkit.support.server.message.api.entity.request.SendCodeRequest;
import org.zkit.support.server.message.api.service.MailCodeApiService;
import org.zkit.support.server.message.service.EmailCodeService;

@DubboService
public class MailCodeApiServiceImpl implements MailCodeApiService {

    @Resource
    private EmailCodeService emailCodeService;

    @Override
    public void send(SendCodeRequest request) {
        emailCodeService.send(request.getEmail(), request.getAction());
    }

    @Override
    public Boolean check(CheckCodeRequest request) {
        return emailCodeService.check(request.getEmail(), request.getCode(), request.getAction());
    }
}
