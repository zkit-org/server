package org.zkit.support.server.account.access.mapper;
import java.util.List;
import java.util.Collection;
import org.apache.ibatis.annotations.Param;

import org.zkit.support.server.account.access.entity.dto.AccessRoleAuthority;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 角色包含的权限 Mapper 接口
 * </p>
 *
 * @author generator
 * @since 2024-05-11
 */
public interface AccessRoleAuthorityMapper extends BaseMapper<AccessRoleAuthority> {

    int deleteByAuthorityId(@Param("authorityId") Long authorityId);
    int deleteByAuthorityIdIn(@Param("authorityIdList") Collection<Long> authorityIdList);
    int deleteByRoleId(@Param("roleId") Long roleId);
    List<AccessRoleAuthority> findByRoleId(@Param("roleId") Long roleId);

}
