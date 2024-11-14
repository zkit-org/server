package org.zkit.support.server.account.access.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.zkit.support.server.account.access.entity.dto.AccessRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.zkit.support.server.account.access.entity.request.AccessRoleQueryRequest;

import java.util.List;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author generator
 * @since 2024-05-11
 */
public interface AccessRoleMapper extends BaseMapper<AccessRole> {

    List<AccessRole> query(IPage<Module> page, @Param("query") AccessRoleQueryRequest query);

}
