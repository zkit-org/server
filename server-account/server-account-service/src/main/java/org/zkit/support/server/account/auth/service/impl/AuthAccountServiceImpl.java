package org.zkit.support.server.account.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.zkit.support.cloud.starter.entity.CreateTokenData;
import org.zkit.support.cloud.starter.service.TokenService;
import org.zkit.support.redisson.starter.DistributedLock;
import org.zkit.support.server.account.api.entity.request.CreateTokenRequest;
import org.zkit.support.server.account.api.entity.response.TokenResponse;
import org.zkit.support.server.account.auth.entity.dto.AuthAccount;
import org.zkit.support.server.account.auth.mapper.AuthAccountMapper;
import org.zkit.support.server.account.auth.service.AuthAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author generator
 * @since 2024-05-04
 */
@Service
public class AuthAccountServiceImpl extends ServiceImpl<AuthAccountMapper, AuthAccount> implements AuthAccountService {

    private TokenService tokenService;

    @Cacheable(value = "account", key = "#username")
    @Override
    public AuthAccount findByUsername(String username) {
        return baseMapper.findOneByUsername(username);
    }

    @Override
    @DistributedLock(value = "auth:account")
    public AuthAccount add(AuthAccount account) {
        account.setCreateTime(new Date());
        this.save(account);
        return account;
    }

    @Override
    public TokenResponse createToken(CreateTokenRequest request) {
        AuthAccount account = getById(request.getId());
        // TODO
        // await this.loadAccessData(account, options);
        CreateTokenData data = new CreateTokenData();
        data.setId(account.getId());
        data.setUsername(account.getUsername());
        data.setExpiresIn(request.getExpiresIn());
        String token = this.tokenService.create(data);
        TokenResponse response = new TokenResponse();
        response.setToken(token);
        response.setExpiresIn(data.getExpiresIn());
        return response;
    }

    @Autowired
    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }
}
