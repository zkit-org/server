package org.zkit.support.server.account.auth.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Headers;
import org.zkit.support.server.account.api.entity.request.AccountLoginRequest;
import org.zkit.support.server.account.api.entity.request.CreateTokenRequest;
import org.zkit.support.server.account.api.entity.response.TokenResponse;
import org.zkit.support.server.account.auth.entity.dto.AuthApp;
import org.zkit.support.server.account.auth.entity.dto.AuthOpenUser;
import org.zkit.support.server.account.auth.entity.mapstruct.AuthOpenUserMapStruct;
import org.zkit.support.server.account.auth.entity.request.AuthLinkBindRequest;
import org.zkit.support.server.account.auth.entity.response.AuthOpenUserResponse;
import org.zkit.support.server.account.auth.mapper.AuthAppMapper;
import org.zkit.support.server.account.auth.mapper.AuthOpenUserMapper;
import org.zkit.support.server.account.auth.service.AuthAccountService;
import org.zkit.support.server.account.auth.service.AuthAppService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.zkit.support.starter.boot.exception.ResultException;
import org.zkit.support.starter.boot.okhttp.HTTPService;
import org.zkit.support.starter.redisson.DistributedLock;
import org.zkit.support.starter.security.entity.CreateTokenData;
import org.zkit.support.starter.security.entity.SessionUser;
import org.zkit.support.starter.security.service.SessionService;
import org.zkit.support.starter.security.service.TokenService;

import java.io.IOException;
import java.util.Date;

/**
 * <p>
 * 第三方应用 服务实现类
 * </p>
 *
 * @author generator
 * @since 2024-05-11
 */
@Service
@Slf4j
public class AuthAppServiceImpl extends ServiceImpl<AuthAppMapper, AuthApp> implements AuthAppService {

    @Resource
    private HTTPService httpService;
    @Resource
    private AuthOpenUserMapper authOpenUserMapper;
    @Resource
    private AuthAccountService authAccountService;
    @Resource
    private AuthOpenUserMapStruct authOpenUserMapStruct;
    @Resource
    private TokenService tokenService;
    @Resource
    private SessionService sessionService;

    @Override
    public void redirect(HttpServletResponse response, String type) throws IOException {
        AuthApp app = baseMapper.findOneByName(type);
        if(app == null)
            throw ResultException.notfound();
        if ("github".equals(type)) {
            response.sendRedirect(app.getAuthorizeUrl() + "?client_id=" + app.getClientId());
        } else if ("wechat".equals(type)) {
            response.sendRedirect(app.getAuthorizeUrl() + "?appid=" + app.getClientId() + "&redirect_uri=" + app.getCallbackUrl() + "&scope=snsapi_login&response_type=code#wechat_redirect");
        }else{
            throw ResultException.notfound();
        }
    }

    @Override
    public AuthOpenUserResponse info(String type, String code) {
        AuthApp app = baseMapper.findOneByName(type);
        if(app == null)
            throw ResultException.notfound();
        if ("github".equals(type)) {
            return githubInfo(code, app);
        } else if ("wechat".equals(type)) {
            return wechatInfo(code, app);
        }
        throw ResultException.internal();
    }

    private AuthOpenUserResponse wechatInfo(String code, AuthApp app) {
        // 请求 access token
        String url = app.getTokenUrl() + "?appid=" + app.getClientId() + "&secret=" + app.getClientSecret() + "&code=" + code + "&grant_type=authorization_code";
        log.info("wechatInfo url: {}", url);
        String tokenResultString = httpService.get(url);
        JSONObject tokenResult = JSON.parseObject(tokenResultString);
        String accessToken = tokenResult.getString("access_token");
        String openId = tokenResult.getString("openid");
        log.info("wechatInfo tokenResult: {}", tokenResult);

        // 请求用户信息
        String infoUrl = app.getInfoUrl() + "?access_token=" + accessToken + "&openid=" + openId;
        log.info("wechatInfo infoUrl: {}", infoUrl);
        String infoResultString = httpService.get(infoUrl);
        JSONObject infoResult = JSON.parseObject(infoResultString);
        log.info("wechatInfo infoResult: {}", infoResult);
        String nickname = infoResult.getString("nickname");
        String headImgUrl = infoResult.getString("headimgurl");

        AuthOpenUser openUser = new AuthOpenUser();
        openUser.setUsername(nickname);
        openUser.setAvatar(headImgUrl);
        openUser.setAccessToken(accessToken);
        openUser.setTokenType("query");
        openUser.setOpenId(openId);
        openUser.setPlatform("wechat");
        return infoOrLoginToken(openUser);
    }

    private AuthOpenUserResponse githubInfo(String code, AuthApp app) {
        // 请求 access token
        String url = app.getTokenUrl() + "?client_id=" + app.getClientId() + "&client_secret=" + app.getClientSecret() + "&code=" + code;
        log.info("githubInfo url: {}", url);
        Headers headers = new Headers.Builder().add("Accept", "application/json").build();
        String tokenResultString = httpService.get(url, headers);
        JSONObject tokenResult = JSON.parseObject(tokenResultString);
        String accessToken = tokenResult.getString("access_token");
        String tokenType = tokenResult.getString("token_type");
        log.info("githubInfo tokenResult: {}", tokenResult);

        // 请求用户信息
        String infoUrl = app.getInfoUrl();
        log.info("githubInfo infoUrl: {}", infoUrl);
        Headers infoHeaders = new Headers.Builder().add("Authorization", tokenType + " " + accessToken).add("Accept", "application/json").build();
        String infoResultString = httpService.get(infoUrl, infoHeaders);
        JSONObject infoResult = JSON.parseObject(infoResultString);
        log.info("githubInfo infoResult: {}", infoResult);
        String login = infoResult.getString("login");
        String avatarUrl = infoResult.getString("avatar_url");
        String nodeId = infoResult.getString("node_id");

        AuthOpenUser openUser = new AuthOpenUser();
        openUser.setUsername(login);
        openUser.setAvatar(avatarUrl);
        openUser.setAccessToken(accessToken);
        openUser.setTokenType(tokenType);
        openUser.setOpenId(nodeId);
        openUser.setPlatform("github");
        return infoOrLoginToken(openUser);
    }

    private AuthOpenUserResponse infoOrLoginToken(AuthOpenUser openUser) {
        AuthOpenUser user = addOrUpdateOpenUser(openUser);
        if(user.getAccountId() != null) {
            CreateTokenRequest createTokenRequest = new CreateTokenRequest();
            createTokenRequest.setId(user.getAccountId());
            createTokenRequest.setExpiresIn(30 * 24 * 60 * 60 * 1000L);
            TokenResponse tokenResponse = authAccountService.createToken(createTokenRequest);
            AuthOpenUserResponse userResponse = authOpenUserMapStruct.toAuthOpenUserResponseFromToken(tokenResponse);
            userResponse.setIsBind(true);
            return userResponse;
        }

        CreateTokenData data = new CreateTokenData();
        data.setId(user.getId());
        data.setUsername(user.getUsername());
        data.setExpiresIn(5 * 60 * 1000L);
        String jwtToken = this.tokenService.create(data);

        AuthOpenUserResponse response = authOpenUserMapStruct.toAuthOpenUserResponse(openUser);
        response.setToken(jwtToken);
        response.setId(null);
        response.setAccountId(null);
        response.setOpenId(null);
        response.setAccessToken(null);
        response.setTokenType(null);
        response.setIsBind(false);
        return response;
    }

    @DistributedLock(value = "account:open:user", el = false)
    private AuthOpenUser addOrUpdateOpenUser(AuthOpenUser openUser) {
        log.info("addOrUpdateOpenUser openUser: {}", openUser);
        AuthOpenUser origin = authOpenUserMapper.findOneByPlatformAndOpenId(openUser.getPlatform(), openUser.getOpenId());
        if(origin != null) {
            origin.setAccessToken(openUser.getAccessToken());
            origin.setTokenType(openUser.getTokenType());
            origin.setUsername(openUser.getUsername());
            origin.setAvatar(openUser.getAvatar());
            origin.setUpdateTime(new Date());
            authOpenUserMapper.updateById(origin);
        }else{
            openUser.setCreateTime(new Date());
            authOpenUserMapper.insert(openUser);
            origin = openUser;
        }
        return origin;
    }

    @Override
    public TokenResponse bind(AuthLinkBindRequest request) {
        boolean checked = tokenService.checked(request.getToken());
        if(!checked)
            throw ResultException.unauthorized();

        AccountLoginRequest loginRequest = new AccountLoginRequest();
        loginRequest.setUsername(request.getAccount());
        loginRequest.setPassword(request.getPassword());
        TokenResponse tokenResponse = authAccountService.login(loginRequest);

        SessionUser user = sessionService.get(tokenResponse.getToken());
        Jws<Claims> jws = tokenService.parse(request.getToken());
        Long openUserId = Long.parseLong(jws.getPayload().getId());

        authOpenUserMapper.updateAccountIdById(user.getId(), openUserId);
        return tokenResponse;
    }
}
