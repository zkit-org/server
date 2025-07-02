package org.zkit.support.server.message.activity.service;

import org.zkit.support.server.message.activity.entity.dto.ActivityApp;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 应用 服务类
 * </p>
 *
 * @author generator
 * @since 2025-07-02
 */
public interface ActivityAppService extends IService<ActivityApp> {

    ActivityApp getApp(String app);

}
