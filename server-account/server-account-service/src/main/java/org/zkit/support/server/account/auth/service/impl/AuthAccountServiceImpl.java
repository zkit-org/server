package org.zkit.support.server.account.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.zkit.support.server.account.api.entity.request.AccountLoginRequest;
import org.zkit.support.server.account.api.entity.request.ResetPasswordRequest;
import org.zkit.support.server.account.auth.entity.dto.AuthAccount;
import org.zkit.support.server.account.auth.entity.enums.AuthAccountEnum;
import org.zkit.support.server.account.auth.service.AuthAccountRoleService;
import org.zkit.support.server.account.auth.service.AuthOTPService;
import org.zkit.support.server.account.configuration.AccountConfiguration;
import org.zkit.support.starter.boot.exception.ResultException;
import org.zkit.support.starter.boot.utils.AESUtils;
import org.zkit.support.starter.boot.utils.RSAUtils;
import org.zkit.support.starter.boot.utils.MD5Utils;
import org.zkit.support.starter.boot.utils.MessageUtils;
import org.zkit.support.starter.redisson.DistributedLock;
import org.zkit.support.server.account.access.entity.dto.AccessApi;
import org.zkit.support.server.account.access.entity.dto.AccessAuthority;
import org.zkit.support.server.account.access.entity.mapstruct.AccessApiMapStruct;
import org.zkit.support.server.account.access.mapper.AccessApiMapper;
import org.zkit.support.server.account.access.mapper.AccessAuthorityMapper;
import org.zkit.support.server.account.api.entity.code.AccountCode;
import org.zkit.support.server.account.api.entity.request.CreateTokenRequest;
import org.zkit.support.server.account.api.entity.request.SetPasswordRequest;
import org.zkit.support.server.account.api.entity.response.OTPResponse;
import org.zkit.support.server.account.api.entity.response.TokenResponse;
import org.zkit.support.server.account.auth.entity.response.AuthAccountAccessData;
import org.zkit.support.server.account.auth.mapper.AuthAccountMapper;
import org.zkit.support.server.account.auth.service.AuthAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.zkit.support.starter.security.entity.CreateTokenData;
import org.zkit.support.starter.security.entity.SessionUser;
import org.zkit.support.starter.security.otp.OTPService;
import org.zkit.support.starter.security.service.SessionService;
import org.zkit.support.starter.security.service.TokenService;

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
@Slf4j
public class AuthAccountServiceImpl extends ServiceImpl<AuthAccountMapper, AuthAccount> implements AuthAccountService {

    @Resource
    private TokenService tokenService;
    @Resource
    private SessionService sessionService;
    @Resource
    private AccessAuthorityMapper accessAuthorityMapper;
    @Resource
    private AccessApiMapper accessApiMapper;
    @Resource
    private AccessApiMapStruct accessApiMapStruct;
    @Resource
    private AuthOTPService authOTPService;
    @Resource
    private AuthAccountRoleService authAccountRoleService;
    @Resource
    private OTPService otpService;
    @Resource
    private AccountConfiguration configuration;

    @Cacheable(value = "auth:account#1d", key = "#username")
    @Override
    public AuthAccount findByUsername(String username) {
        return baseMapper.findOneByUsername(username);
    }

    @Override
    @DistributedLock(value = "auth:account", el = false)
    public AuthAccount add(AuthAccount account) {
        account.setCreateTime(new Date());
        this.save(account);
        return account;
    }

    @Override
    @CacheEvict(value = "auth:account", key = "#result.username")
    @DistributedLock("'auth:account:'+#request.id")
    @Transactional
    public TokenResponse setPassword(SetPasswordRequest request) {
        AuthAccount account = getById(request.getId());
        if(account.getOtpStatus() == 1) {
            throw new ResultException(AccountCode.OTP_IS_BIND.code, MessageUtils.get(AccountCode.OTP_IS_BIND.key));
        }
        boolean verified = authOTPService.check(account.getOtpSecret(), request.getCode());
        if(!verified) {
            throw new ResultException(AccountCode.OTP_ERROR.code, MessageUtils.get(AccountCode.OTP_ERROR.key));
        }
        String password = RSAUtils.decrypt(request.getPassword(), configuration.getTransportPrivateKey());
        log.info("password: {}", password);
        password = AESUtils.encrypt(password, configuration.getAesKey());

        // 更新账号信息
        UpdateWrapper<AuthAccount> update = new UpdateWrapper<>();
        update.eq("id",account.getId());
        update
                .set("password", password)
                .set("otp_status", 1)
                .set("status", 1);
        getBaseMapper().update(update);

        // 保存角色
        String defaultRole = configuration.getDefaultRole();
        if(defaultRole != null) {
            authAccountRoleService.addRoles(account.getId(), List.of(defaultRole));
        }

        // 生成token
        CreateTokenRequest tokenRequest = new CreateTokenRequest(request.getId(), configuration.getExpiresIn());
        TokenResponse response = this.createToken(tokenRequest);
        response.setUsername(account.getUsername());
        return response;
    }

    @Override
    @DistributedLock(value = "auth:account", el = false)
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
        if (configuration.getSu().equals(account.getUsername())) { // 超管
            List<AccessAuthority> all = accessAuthorityMapper.selectList(null);
            authorityNames = all.stream().map(AccessAuthority::getValue).toList();
            authorityIds = all.stream().map(AccessAuthority::getId).toList();
        } else { // 普通用户
            // 这里需要根据实际情况调整查询逻辑
            List<AccessAuthority> authorities = accessAuthorityMapper.findAuthoritiesByAccountId(account.getId());
            authorityNames = authorities.stream().map(AccessAuthority::getValue).collect(Collectors.toList());
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

    @Override
    @CacheEvict(value = "auth:account", key = "#result.username")
    @DistributedLock("'auth:account'+#id")
    public OTPResponse otpSecret(Long id) {
        AuthAccount account = getById(id);
        if(account.getOtpStatus() == 1) {
            throw new ResultException(AccountCode.OTP_IS_BIND.code, MessageUtils.get(AccountCode.OTP_IS_BIND.key));
        }
        String key = otpService.generateSecretKey();
        String qrCode = otpService.getQRCode(account.getUsername(), key);

        // 更新otp信息
        UpdateWrapper<AuthAccount> update = new UpdateWrapper<>();
        update.eq("id",account.getId());
        update.set("otp_secret", key);
        getBaseMapper().update(update);

        OTPResponse response = new OTPResponse();
        response.setSecret(key);
        response.setQrcode(qrCode);
        response.setUsername(account.getUsername());
        return response;
    }

    @Override
    public TokenResponse login(AccountLoginRequest request) {
        String code = request.getCode();
        String password = RSAUtils.decrypt(request.getPassword(), configuration.getTransportPrivateKey());
        AuthAccount account = baseMapper.findOneByUsername(request.getUsername());
        if(account == null) {
            throw new ResultException(AccountCode.LOGIN_ERROR.code, MessageUtils.get(AccountCode.LOGIN_ERROR.key));
        }
        if(!password.equals(AESUtils.decrypt(account.getPassword(), configuration.getAesKey()))) {
            throw new ResultException(AccountCode.LOGIN_ERROR.code, MessageUtils.get(AccountCode.LOGIN_ERROR.key));
        }
        if(code != null) {
            if(configuration.getResetOtpCode().equals(code)) { // 重置OTP
                if(account.getOtpStatus() == 0) {
                    return this.createToken(new CreateTokenRequest(account.getId(), 5 * 60 * 1000L));
                }else{
                    throw new ResultException(AccountCode.OTP_IS_BIND.code, MessageUtils.get(AccountCode.OTP_IS_BIND.key));
                }
            }else{
                boolean verified = authOTPService.check(account.getOtpSecret(), code);
                if(!verified) {
                    throw new ResultException(AccountCode.OTP_ERROR.code, MessageUtils.get(AccountCode.OTP_ERROR.key));
                }
            }
        }
        return this.createToken(new CreateTokenRequest(account.getId(), configuration.getExpiresIn()));
    }

    @Override
    public TokenResponse resetPassword(ResetPasswordRequest request) {
        AuthAccount account = getById(request.getId());
        if(account.getStatus() != AuthAccountEnum.STATUS_NORMAL.code) {
            throw new ResultException(AccountCode.NOTFOUND.code, MessageUtils.get(AccountCode.NOTFOUND.key));
        }
        String password = RSAUtils.decrypt(request.getPassword(), configuration.getTransportPrivateKey());
        password = AESUtils.encrypt(password, configuration.getAesKey());
        UpdateWrapper<AuthAccount> update = new UpdateWrapper<>();
        update.eq("id",account.getId());
        update.set("password", password);
        getBaseMapper().update(update);
        return this.createToken(new CreateTokenRequest(account.getId(), configuration.getExpiresIn()));
    }
}
