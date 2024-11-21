package org.zkit.support.server.account.access.entity.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.zkit.support.server.account.access.entity.dto.AccessRole;
import org.zkit.support.server.account.access.entity.request.AccessRoleRequest;
import org.zkit.support.server.account.access.entity.response.AccessRoleResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccessRoleMapStruct {

    AccessRole toAccessRole(AccessRoleRequest request);
    AccessRoleResponse toAccessRoleResponse(AccessRole role);

}
