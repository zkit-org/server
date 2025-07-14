package org.zkit.support.server.message.activity.service;

import org.zkit.support.server.message.activity.entity.dto.Activity;
import org.zkit.support.server.message.api.entity.request.ActivityListRequest;
import org.zkit.support.server.message.api.entity.request.ActivityRequest;
import org.zkit.support.server.message.api.entity.response.ActivityResponse;
import org.zkit.support.starter.mybatis.entity.PageResult;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 动态 服务类
 * </p>
 *
 * @author generator
 * @since 2025-07-01
 */
public interface ActivityService extends IService<Activity> {

    void save(ActivityRequest request);
    PageResult<ActivityResponse> list(ActivityListRequest request);

}
