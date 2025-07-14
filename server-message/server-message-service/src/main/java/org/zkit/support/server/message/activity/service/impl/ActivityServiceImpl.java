package org.zkit.support.server.message.activity.service.impl;

import org.zkit.support.server.message.activity.entity.dto.Activity;
import org.zkit.support.server.message.activity.entity.dto.ActivityApp;
import org.zkit.support.server.message.activity.mapper.ActivityMapper;
import org.zkit.support.server.message.activity.service.ActivityService;
import org.zkit.support.server.message.api.entity.request.ActivityListRequest;
import org.zkit.support.server.message.api.entity.request.ActivityRequest;
import org.zkit.support.server.message.api.entity.response.ActivityResponse;
import org.zkit.support.starter.mybatis.entity.PageRequest;
import org.zkit.support.starter.mybatis.entity.PageResult;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.zkit.support.server.message.activity.entity.mapstruct.ActivityAppMapStruct;
import org.zkit.support.server.message.activity.entity.mapstruct.ActivityMapStruct;
import org.zkit.support.server.message.activity.service.ActivityAppService;
import org.zkit.support.server.message.activity.service.ActivityNoticeService;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 动态 服务实现类
 * </p>
 *
 * @author generator
 * @since 2025-07-01
 */
@Service
@Slf4j
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {

    @Resource
    private ActivityMapStruct activityMapStruct;
    @Resource
    private ActivityNoticeService activityNoticeService;
    @Resource
    private ActivityAppService activityAppService;
    @Resource
    private ActivityAppMapStruct activityAppMapStruct;

    @Override
    public void save(ActivityRequest request) {
        Activity activity = activityMapStruct.toActivity(request);
        ActivityApp app = activityAppService.getApp(request.getApp());
        activity.setAppId(app.getId());
        this.save(activity);
        activityNoticeService.addUser(activity.getId(), request.getNoticeUserIds());
    }

    @Override
    public PageResult<ActivityResponse> list(ActivityListRequest request) {
        log.info("list request: {}", request.getMetadata().keySet());
        try{
            PageRequest pr = PageRequest.of(request.getPage(), request.getSize());
            try (Page<Activity> page = pr.start()) {
                baseMapper.query(request);
                List<Activity> activities = page.getResult();
                List<Long> appIds = activities.stream()
                    .map(Activity::getAppId)
                    .distinct()
                    .toList();
                List<ActivityApp> apps = activityAppService.listByIds(appIds);
                List<ActivityResponse> responses = activities.stream()
                    .map(activity -> {
                        ActivityResponse response = activityMapStruct.toActivityResponse(activity);
                        ActivityApp app = apps.stream()
                            .filter(a -> a.getId().equals(activity.getAppId()))
                            .findFirst()
                            .orElse(null);
                        response.setApp(app != null ? activityAppMapStruct.toApp(app) : null);
                        return response;
                    })
                    .toList();
                return PageResult.of(page.getTotal(), responses);
            }
        }catch(Exception e){
            log.error("list error: {}", e.getMessage(), e);
            return PageResult.of(0, Collections.emptyList());
        }
    }

}
