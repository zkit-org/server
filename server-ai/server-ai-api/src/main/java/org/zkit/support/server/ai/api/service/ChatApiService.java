package org.zkit.support.server.ai.api.service;

import java.util.List;

import org.zkit.support.server.ai.api.entity.InvokeRequest;

public interface ChatApiService {

    String invoke(InvokeRequest request);

}
