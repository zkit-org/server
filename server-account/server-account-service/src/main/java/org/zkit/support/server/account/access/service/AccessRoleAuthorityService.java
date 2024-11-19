package org.zkit.support.server.account.access.service;

import org.zkit.support.server.account.access.entity.dto.AccessRoleAuthority;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色包含的权限 服务类
 * </p>
 *
 * @author generator
 * @since 2024-05-11
 */
public interface AccessRoleAuthorityService extends IService<AccessRoleAuthority> {

    void deleteByAuthorityId(Long authorityId);
    void deleteByAuthorityIds(List<Long> authorityIds);

}
