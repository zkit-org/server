package org.zkit.support.server.account.access.entity.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.zkit.support.cloud.starter.entity.SessionUser;
import org.zkit.support.server.account.access.entity.dto.AccessApi;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccessApiMapStruct {

    SessionUser.Api toSessionApi(AccessApi api);

}
