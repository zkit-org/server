package org.zkit.support.server.message.api.service;

import org.zkit.support.server.message.api.entity.request.CheckCodeRequest;
import org.zkit.support.server.message.api.entity.request.SendCodeRequest;
import org.zkit.support.server.message.api.entity.request.SendMailRequest;

public interface MailApiService {

    void send(SendMailRequest request);
    void sendCode(SendCodeRequest request);
    Boolean check(CheckCodeRequest request);

}
