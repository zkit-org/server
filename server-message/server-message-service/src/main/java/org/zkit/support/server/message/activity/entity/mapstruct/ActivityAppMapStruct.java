package org.zkit.support.server.message.activity.entity.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.zkit.support.server.message.activity.entity.dto.ActivityApp;
import org.zkit.support.server.message.api.entity.response.ActivityResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ActivityAppMapStruct {

    ActivityResponse.App toApp(ActivityApp app);

}
