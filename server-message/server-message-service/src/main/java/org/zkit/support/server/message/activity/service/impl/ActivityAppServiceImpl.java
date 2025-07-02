package org.zkit.support.server.message.activity.service.impl;

import org.zkit.support.server.message.activity.entity.dto.ActivityApp;
import org.zkit.support.server.message.activity.mapper.ActivityAppMapper;
import org.zkit.support.server.message.activity.service.ActivityAppService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 应用 服务实现类
 * </p>
 *
 * @author generator
 * @since 2025-07-02
 */
@Service
public class ActivityAppServiceImpl extends ServiceImpl<ActivityAppMapper, ActivityApp> implements ActivityAppService {

    @Override
    public ActivityApp getApp(String app) {
        return lambdaQuery().eq(ActivityApp::getLabel, app).one();
    }

}
