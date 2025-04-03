package org.zkit.support.server.mail.api.service;

import org.zkit.support.server.mail.api.entity.request.CheckCodeRequest;
import org.zkit.support.server.mail.api.entity.request.SendCodeRequest;
import org.zkit.support.server.mail.api.entity.request.SendMailRequest;

public interface MailApiService {

    void send(SendMailRequest request);
    void sendCode(SendCodeRequest request);
    Boolean check(CheckCodeRequest request);

}
