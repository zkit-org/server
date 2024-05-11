package org.zkit.support.server.account.auth.service;

import org.zkit.support.server.account.api.entity.request.CreateTokenRequest;
import org.zkit.support.server.account.api.entity.request.SetPasswordRequest;
import org.zkit.support.server.account.api.entity.response.OTPResponse;
import org.zkit.support.server.account.api.entity.response.TokenResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import org.zkit.support.server.account.auth.entity.dto.AuthAccount;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author generator
 * @since 2024-05-04
 */
public interface AuthAccountService extends IService<AuthAccount> {

    AuthAccount findByUsername(String username);
    AuthAccount add(AuthAccount account);
    AuthAccount addOrGet(AuthAccount account);
    TokenResponse createToken(CreateTokenRequest request);
    OTPResponse otpSecret(Long id);
    TokenResponse setPassword(SetPasswordRequest request);

}
