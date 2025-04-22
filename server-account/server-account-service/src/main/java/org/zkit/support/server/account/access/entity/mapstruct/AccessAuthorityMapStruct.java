package org.zkit.support.server.account.access.entity.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.zkit.support.server.account.access.entity.dto.AccessAuthority;
import org.zkit.support.server.account.access.entity.request.AccessAuthorityRequest;
import org.zkit.support.server.account.access.entity.response.AccessAuthorityResponse;
import org.zkit.support.server.account.access.entity.response.AccessAuthorityTreeResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface AccessAuthorityMapStruct {

    AccessAuthorityTreeResponse toAccessAuthorityTreeResponse(AccessAuthority accessAuthority);
    AccessAuthority fromAccessAuthorityRequest(AccessAuthorityRequest request);
    AccessAuthorityResponse toAccessAuthorityResponse(AccessAuthority accessAuthority);

}
