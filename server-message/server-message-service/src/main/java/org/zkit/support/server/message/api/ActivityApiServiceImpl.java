package org.zkit.support.server.message.api;

import org.apache.dubbo.config.annotation.DubboService;
import org.zkit.support.server.message.activity.service.ActivityService;
import org.zkit.support.server.message.api.entity.request.ActivityListRequest;
import org.zkit.support.server.message.api.entity.response.ActivityResponse;
import org.zkit.support.server.message.api.service.ActivityApiService;
import org.zkit.support.starter.mybatis.entity.PageResult;

import jakarta.annotation.Resource;

@DubboService
public class ActivityApiServiceImpl implements ActivityApiService {

    @Resource
    private ActivityService activityService;

    @Override
    public PageResult<ActivityResponse> list(ActivityListRequest request) {
        return activityService.list(request);
    }
}
