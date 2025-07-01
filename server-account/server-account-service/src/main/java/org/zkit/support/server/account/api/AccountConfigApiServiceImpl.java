package org.zkit.support.server.account.api;

import org.apache.dubbo.config.annotation.DubboService;
import org.zkit.support.server.account.api.service.AccountConfigApiService;
import org.zkit.support.server.account.configuration.AccountConfiguration;

import jakarta.annotation.Resource;

@DubboService
public class AccountConfigApiServiceImpl implements AccountConfigApiService {

    @Resource
    private AccountConfiguration configuration;

    @Override
    public String getTransportPublicKey() {
        return configuration.getTransportPublicKey();
    }
}
