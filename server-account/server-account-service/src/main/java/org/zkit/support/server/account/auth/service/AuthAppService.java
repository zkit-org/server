package org.zkit.support.server.account.auth.service;

import jakarta.servlet.http.HttpServletResponse;
import org.zkit.support.server.account.api.entity.response.TokenResponse;
import org.zkit.support.server.account.auth.entity.dto.AuthApp;
import com.baomidou.mybatisplus.extension.service.IService;
import org.zkit.support.server.account.auth.entity.request.AuthLinkBindRequest;
import org.zkit.support.server.account.auth.entity.response.AuthOpenUserResponse;

import java.io.IOException;

/**
 * <p>
 * 第三方应用 服务类
 * </p>
 *
 * @author generator
 * @since 2024-05-11
 */
public interface AuthAppService extends IService<AuthApp> {

    void redirect(HttpServletResponse response, String type) throws IOException;
    AuthOpenUserResponse info(String type, String code);
    TokenResponse bind(AuthLinkBindRequest request);

}
