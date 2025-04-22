package org.zkit.support.server.account.auth.entity.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.zkit.support.server.account.api.entity.request.AccountAddRequest;
import org.zkit.support.server.account.api.entity.request.AccountRegisterRequest;
import org.zkit.support.server.account.api.entity.response.AccountResponse;
import org.zkit.support.server.account.api.entity.response.TokenResponse;
import org.zkit.support.server.account.api.entity.response.TokenWithAccountResponse;
import org.zkit.support.server.account.auth.entity.dto.AuthAccount;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface AuthAccountMapStruct {

    AccountResponse toAccountResponse(AuthAccount request);
    AuthAccount toAuthAccount(AccountAddRequest request);
    AuthAccount toAuthAccountFromRegister(AccountRegisterRequest request);
    TokenWithAccountResponse toTokenWithAccountResponse(TokenResponse response);

}
