package org.zkit.support.server.account.api.service;

import org.zkit.support.server.account.api.entity.request.*;
import org.zkit.support.server.account.api.entity.response.AccountResponse;
import org.zkit.support.server.account.api.entity.response.TokenResponse;
import org.zkit.support.server.account.api.entity.response.TokenWithAccountResponse;

public interface AccountApiService {

    AccountResponse findByUsername(String username);
    AccountResponse add(AccountAddRequest request);
    AccountResponse addOrGet(AccountAddRequest request);
    TokenResponse createToken(CreateTokenRequest request);
    TokenWithAccountResponse register(AccountRegisterRequest request);
    TokenResponse login(AccountLoginRequest request);
    void logout(String token);
    TokenResponse resetPassword(ResetPasswordRequest request);
    void changePassword(ChangePasswordRequest request);

}
