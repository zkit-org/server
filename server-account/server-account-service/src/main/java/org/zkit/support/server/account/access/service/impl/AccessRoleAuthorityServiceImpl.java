package org.zkit.support.server.account.access.service.impl;

import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.context.annotation.Lazy;
import org.zkit.support.server.account.access.entity.dto.AccessAuthority;
import org.zkit.support.server.account.access.entity.dto.AccessRoleAuthority;
import org.zkit.support.server.account.access.entity.response.AccessAuthorityTreeResponse;
import org.zkit.support.server.account.access.mapper.AccessRoleAuthorityMapper;
import org.zkit.support.server.account.access.service.AccessAuthorityService;
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
@Slf4j
public class AccessRoleAuthorityServiceImpl extends ServiceImpl<AccessRoleAuthorityMapper, AccessRoleAuthority> implements AccessRoleAuthorityService {

    @Resource
    @Lazy
    private AccessAuthorityService accessAuthorityService;

    @Override
    public void deleteByAuthorityId(Long authorityId) {
        baseMapper.deleteByAuthorityId(authorityId);
    }

    @Override
    public void deleteByAuthorityIds(List<Long> authorityIds) {
        baseMapper.deleteByAuthorityIdIn(authorityIds);
    }

    @Override
    @Transactional
    public void saveAuthorities(Long roleId, List<Long> authorityIds) {
        baseMapper.deleteByRoleId(roleId);
        if (authorityIds != null && !authorityIds.isEmpty()) {
            List<AccessRoleAuthority> aras = authorityIds.stream().map(authorityId -> {
                AccessRoleAuthority ara = new AccessRoleAuthority();
                ara.setRoleId(roleId);
                ara.setAuthorityId(authorityId);
                return ara;
            }).toList();
            AccessRoleAuthorityService service = (AccessRoleAuthorityService) AopContext.currentProxy();
            service.saveBatch(aras);
        }
    }

    @Override
    public List<Long> authorityIds(Long roleId) {
        List<AccessRoleAuthority> aras = baseMapper.findByRoleId(roleId);
        return aras.stream().map(AccessRoleAuthority::getAuthorityId).toList();
    }

    @Override
    public List<AccessAuthorityTreeResponse> tree(Long roleId) {
        List<Long> ids = authorityIds(roleId);
        List<AccessAuthority> all = accessAuthorityService.list();
        List<Long> allParentIds = new java.util.ArrayList<>(List.of());
        ids.forEach(id -> {
            allParentIds.addAll(getAllParentIds(all, id));
        });
        allParentIds.addAll(ids);
        ids = allParentIds.stream().distinct().toList();
        List<AccessAuthority> authorities = accessAuthorityService.findByIdIn(ids);
        return accessAuthorityService.tree(authorities);
    }

    private List<Long> getAllParentIds(List<AccessAuthority> all, Long id) {
        AccessAuthority authority = all.stream().filter(a -> a.getId().equals(id)).findFirst().orElse(null);
        if (authority == null) {
            return List.of();
        }
        List<Long> ids = new java.util.ArrayList<>(List.of());
        if (authority.getParentId() != null) {
            ids.add(authority.getParentId());
            ids.addAll(getAllParentIds(all, authority.getParentId()));
        }
        return ids;
    }

    @Override
    public void deleteByRoleId(Long roleId) {
        baseMapper.deleteByRoleId(roleId);
    }
}
