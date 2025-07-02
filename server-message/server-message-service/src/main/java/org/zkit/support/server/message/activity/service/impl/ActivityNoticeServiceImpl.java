package org.zkit.support.server.message.activity.service.impl;

import org.zkit.support.server.message.activity.entity.dto.ActivityNotice;
import org.zkit.support.server.message.activity.mapper.ActivityNoticeMapper;
import org.zkit.support.server.message.activity.service.ActivityNoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 通知 服务实现类
 * </p>
 *
 * @author generator
 * @since 2025-07-01
 */
@Service
public class ActivityNoticeServiceImpl extends ServiceImpl<ActivityNoticeMapper, ActivityNotice> implements ActivityNoticeService {

    @Override
    public void addUser(Long activityId, List<Long> userIds) {
        if(userIds == null || userIds.size() == 0){
            return;
        }
        List<ActivityNotice> activityNotices = userIds.stream().map(userId -> new ActivityNotice().setActivityId(activityId).setUserId(userId)).collect(Collectors.toList());
        this.saveBatch(activityNotices);
    }

}
