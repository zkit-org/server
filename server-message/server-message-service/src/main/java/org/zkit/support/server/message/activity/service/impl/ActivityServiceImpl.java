package org.zkit.support.server.message.activity.service.impl;

import org.zkit.support.server.message.activity.entity.dto.Activity;
import org.zkit.support.server.message.activity.entity.dto.ActivityApp;
import org.zkit.support.server.message.activity.mapper.ActivityMapper;
import org.zkit.support.server.message.activity.service.ActivityService;
import org.zkit.support.server.message.api.entity.request.ActivityRequest;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.zkit.support.server.message.activity.entity.mapstruct.ActivityMapStruct;
import org.zkit.support.server.message.activity.service.ActivityAppService;
import org.zkit.support.server.message.activity.service.ActivityNoticeService;

import jakarta.annotation.Resource;

/**
 * <p>
 * 动态 服务实现类
 * </p>
 *
 * @author generator
 * @since 2025-07-01
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {

    @Resource
    private ActivityMapStruct activityMapStruct;
    @Resource
    private ActivityNoticeService activityNoticeService;
    @Resource
    private ActivityAppService activityAppService;

    @Override
    public void save(ActivityRequest request) {
        Activity activity = activityMapStruct.toActivity(request);
        ActivityApp app = activityAppService.getApp(request.getApp());
        activity.setAppId(app.getId());
        this.save(activity);
        activityNoticeService.addUser(activity.getId(), request.getNoticeUserIds());
    }

}
