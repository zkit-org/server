package org.zkit.support.server.account.access.service.impl;

import org.zkit.support.server.account.access.entity.dto.AccessRoleAuthority;
import org.zkit.support.server.account.access.mapper.AccessRoleAuthorityMapper;
import org.zkit.support.server.account.access.service.AccessRoleAuthorityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色包含的权限 服务实现类
 * </p>
 *
 * @author generator
 * @since 2024-05-11
 */
@Service
public class AccessRoleAuthorityServiceImpl extends ServiceImpl<AccessRoleAuthorityMapper, AccessRoleAuthority> implements AccessRoleAuthorityService {

    @Override
    public void deleteByAuthorityId(Long authorityId) {
        baseMapper.deleteByAuthorityId(authorityId);
    }

    @Override
    public void deleteByAuthorityIds(List<Long> authorityIds) {
        baseMapper.deleteByAuthorityIdIn(authorityIds);
    }
}
