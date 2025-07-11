package org.zkit.support.server.account.api.service;

import org.zkit.support.server.account.api.entity.request.AuthLinkBindRequest;
import org.zkit.support.server.account.api.entity.response.TokenResponse;

public interface AccountLinkApiService {
    
    TokenResponse bind(AuthLinkBindRequest request);

}
