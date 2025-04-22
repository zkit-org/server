package org.zkit.support.server.account.auth.entity.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.zkit.support.server.account.api.entity.response.TokenResponse;
import org.zkit.support.server.account.auth.entity.dto.AuthOpenUser;
import org.zkit.support.server.account.auth.entity.response.AuthOpenUserResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface AuthOpenUserMapStruct {

    AuthOpenUserResponse toAuthOpenUserResponseFromToken(TokenResponse response);
    AuthOpenUserResponse toAuthOpenUserResponse(AuthOpenUser authOpenUser);

}
