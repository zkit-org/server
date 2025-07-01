package org.zkit.support.server.account.api;

import org.apache.dubbo.config.annotation.DubboService;
import org.zkit.support.server.account.api.entity.request.AuthLinkBindRequest;
import org.zkit.support.server.account.api.entity.response.TokenResponse;
import org.zkit.support.server.account.api.service.AccountLinkApiService;
import org.zkit.support.server.account.auth.service.AuthAppService;

import jakarta.annotation.Resource;

@DubboService
public class AccountLinkApiServiceImpl implements AccountLinkApiService {

    @Resource
    private AuthAppService authAppService;

    @Override
    public TokenResponse bind(AuthLinkBindRequest request) {
        return authAppService.bind(request);
    }
}