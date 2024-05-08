package org.zkit.support.server.account.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.zkit.support.cloud.starter.configuration.AuthConfiguration;
import org.zkit.support.cloud.starter.entity.CreateTokenData;
import org.zkit.support.cloud.starter.entity.SessionUser;
import org.zkit.support.cloud.starter.exception.ResultException;
import org.zkit.support.cloud.starter.service.SessionService;
import org.zkit.support.cloud.starter.service.TokenService;
import org.zkit.support.cloud.starter.utils.MD5Utils;
import org.zkit.support.cloud.starter.utils.MessageUtils;
import org.zkit.support.redisson.starter.DistributedLock;
import org.zkit.support.server.account.access.entity.dto.AccessApi;
import org.zkit.support.server.account.access.entity.dto.AccessAuthority;
import org.zkit.support.server.account.access.entity.mapstruct.AccessApiMapStruct;
import org.zkit.support.server.account.access.mapper.AccessApiMapper;
import org.zkit.support.server.account.access.mapper.AccessAuthorityMapper;
import org.zkit.support.server.account.api.entity.code.AccountCode;
import org.zkit.support.server.account.api.entity.request.CreateTokenRequest;
import org.zkit.support.server.account.api.entity.response.TokenResponse;
import org.zkit.support.server.account.auth.entity.dto.AuthAccount;
import org.zkit.support.server.account.auth.entity.response.AuthAccountAccessData;
import org.zkit.support.server.account.auth.mapper.AuthAccountMapper;
import org.zkit.support.server.account.auth.service.AuthAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    private SessionService sessionService;
    private AuthConfiguration authConfiguration;
    private AccessAuthorityMapper accessAuthorityMapper;
    private AccessApiMapper accessApiMapper;
    private AccessApiMapStruct accessApiMapStruct;

    @Cacheable(value = "account", key = "#username")
    @Override
    public AuthAccount findByUsername(String username) {
        return baseMapper.findOneByUsername(username);
    }

    @Override
    @CachePut(value = "account", key = "#account.username")
    @DistributedLock(value = "auth:account")
    public AuthAccount add(AuthAccount account) {
        account.setCreateTime(new Date());
        this.save(account);
        return account;
    }

    @Override
    @CachePut(value = "account", key = "#account.username")
    @DistributedLock(value = "auth:account")
    public AuthAccount addOrGet(AuthAccount account) {
        AuthAccount origin = baseMapper.findOneByUsername(account.getUsername());
        if(origin != null) {
            if(origin.getOtpStatus() == 1) {
                throw new ResultException(AccountCode.ADD_OTP_ERROR.code, MessageUtils.get(AccountCode.ADD_OTP_ERROR.key));
            }
            return origin;
        }
        account.setCreateTime(new Date());
        this.save(account);
        return account;
    }

    @Override
    public TokenResponse createToken(CreateTokenRequest request) {
        AuthAccount account = getById(request.getId());

        CreateTokenData data = new CreateTokenData();
        data.setId(account.getId());
        data.setUsername(account.getUsername());
        data.setExpiresIn(request.getExpiresIn());
        String jwtToken = this.tokenService.create(data);
        String token = MD5Utils.text(jwtToken);

        // 加载资源
        AuthAccountAccessData accessData = getAccessData(account);
        SessionUser user = new SessionUser();
        user.setId(account.getId());
        user.setUsername(account.getUsername());
        user.setExpiresIn(data.getExpiresIn());
        user.setJwtToken(jwtToken);
        user.setApis(accessData.getApis());
        user.setAuthorities(accessData.getAuthorities());
        sessionService.login(user);

        // 客户端 token
        TokenResponse response = new TokenResponse();
        response.setToken(token);
        response.setExpiresIn(data.getExpiresIn());
        return response;
    }

    public AuthAccountAccessData getAccessData(AuthAccount account) {
        List<String> authorityNames = Collections.emptyList();
        List<Long> authorityIds = Collections.emptyList();
        if (authConfiguration.getSu().equals(account.getUsername())) { // 超管
            List<AccessAuthority> all = accessAuthorityMapper.selectList(null);
            authorityNames = all.stream().map(AccessAuthority::getKey).toList();
            authorityIds = all.stream().map(AccessAuthority::getId).toList();
        } else { // 普通用户
            // 这里需要根据实际情况调整查询逻辑
            List<AccessAuthority> authorities = accessAuthorityMapper.findAuthoritiesByAccountId(account.getId());
            authorityNames = authorities.stream().map(AccessAuthority::getKey).collect(Collectors.toList());
            authorityIds = authorities.stream().map(AccessAuthority::getId).collect(Collectors.toList());
        }
        // 假设这里已经处理了树形结构和扁平化逻辑
        List<AccessApi> apis = authorityIds.isEmpty() ? Collections.emptyList() : accessApiMapper.findApisByAuthorityIds(authorityIds);
        List<SessionUser.Api> sessionApis = apis.stream().map(api -> accessApiMapStruct.toSessionApi(api)).collect(Collectors.toList());

        AuthAccountAccessData data = new AuthAccountAccessData();
        data.setAuthorities(authorityNames);
        data.setApis(sessionApis);
        return data;
    }

    @Autowired
    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Autowired
    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Autowired
    public void setAuthConfiguration(AuthConfiguration authConfiguration) {
        this.authConfiguration = authConfiguration;
    }

    @Autowired
    public void setAccessAuthorityMapper(AccessAuthorityMapper accessAuthorityMapper) {
        this.accessAuthorityMapper = accessAuthorityMapper;
    }

    @Autowired
    public void setAccessApiMapper(AccessApiMapper accessApiMapper) {
        this.accessApiMapper = accessApiMapper;
    }

    @Autowired
    public void setAccessApiMapStruct(AccessApiMapStruct accessApiMapStruct) {
        this.accessApiMapStruct = accessApiMapStruct;
    }
}
