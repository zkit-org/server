package org.zkit.support.server.mail.api.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.zkit.support.server.mail.api.constant.MailApi;
import org.zkit.support.server.mail.api.constant.MailApiRoute;
import org.zkit.support.server.mail.api.entity.request.CheckCodeRequest;
import org.zkit.support.server.mail.api.entity.request.SendCodeRequest;
import org.zkit.support.starter.boot.entity.Result;

@FeignClient(value = MailApi.APP_NAME)
@Service
public interface MailRestApi {

    @PostMapping(value = MailApiRoute.SEND)
    Result<?> send(@RequestBody SendCodeRequest request);

    @PostMapping(value = MailApiRoute.CHECK)
    Result<Boolean> check(@RequestBody CheckCodeRequest request);

}
