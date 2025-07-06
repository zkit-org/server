package org.zkit.support.server.message.activity.entity.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.zkit.support.server.message.activity.entity.dto.Activity;
import org.zkit.support.server.message.api.entity.request.ActivityRequest;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ActivityMapStruct {

    @Mapping(target = "appId", ignore = true)
    @Mapping(target = "id", ignore = true)
    Activity toActivity(ActivityRequest request);

}
