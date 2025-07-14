package org.zkit.support.server.message.api.service;

import org.zkit.support.server.message.api.entity.request.ActivityListRequest;
import org.zkit.support.server.message.api.entity.response.ActivityResponse;
import org.zkit.support.starter.mybatis.entity.PageResult;

public interface ActivityApiService {

    PageResult<ActivityResponse> list(ActivityListRequest request);

}
