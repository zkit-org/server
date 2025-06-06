package org.zkit.support.server.account.auth.mapper;
import org.apache.ibatis.annotations.Param;

import org.zkit.support.server.account.auth.entity.dto.AuthAccountRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 账号的角色 Mapper 接口
 * </p>
 *
 * @author generator
 * @since 2024-05-11
 */
public interface AuthAccountRoleMapper extends BaseMapper<AuthAccountRole> {

    int deleteByAccountId(@Param("accountId") Long accountId);
    int deleteByRoleId(@Param("roleId") Long roleId);

}
