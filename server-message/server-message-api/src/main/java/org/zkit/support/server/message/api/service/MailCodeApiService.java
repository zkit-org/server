package org.zkit.support.server.message.api.service;

import org.zkit.support.server.message.api.entity.request.CheckCodeRequest;
import org.zkit.support.server.message.api.entity.request.SendCodeRequest;

public interface MailCodeApiService {

    void send(SendCodeRequest request);
    Boolean check(CheckCodeRequest request);
    
}
