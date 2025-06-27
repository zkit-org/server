package org.zkit.support.server.ai.api.service;

import org.zkit.support.server.ai.api.entity.InvokeRequest;

public interface ChatApiService {

    String invoke(InvokeRequest request);
    String stream(InvokeRequest request);

}
