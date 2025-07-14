package org.zkit.support.server.message.activity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zkit.support.server.message.activity.entity.dto.Activity;
import org.zkit.support.server.message.api.entity.request.ActivityListRequest;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 动态 Mapper 接口
 * </p>
 *
 * @author generator
 * @since 2025-07-01
 */
public interface ActivityMapper extends BaseMapper<Activity> {

    List<Activity> query(@Param("request") ActivityListRequest request);

}
