package org.zkit.support.server.account.auth.service.impl;

import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.zkit.support.server.account.access.entity.dto.AccessRole;
import org.zkit.support.server.account.access.service.AccessRoleService;
import org.zkit.support.server.account.auth.entity.dto.AuthAccountRole;
import org.zkit.support.server.account.auth.mapper.AuthAccountRoleMapper;
import org.zkit.support.server.account.auth.service.AuthAccountRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 账号的角色 服务实现类
 * </p>
 *
 * @author generator
 * @since 2024-05-11
 */
@Service
public class AuthAccountRoleServiceImpl extends ServiceImpl<AuthAccountRoleMapper, AuthAccountRole> implements AuthAccountRoleService {

    @Resource
    private AccessRoleService accessRoleService;

    @Override
    @Transactional
    public void addRoles(Long accountId, List<String> roles) {
        baseMapper.deleteByAccountId(accountId);
        if(roles.isEmpty()) {
            return;
        }
        List<AccessRole> accessRoles = accessRoleService.findByNameIn(roles);
        if(accessRoles.isEmpty()) {
            return;
        }
        List<AuthAccountRole> accountRoles = roles.stream().map(role -> {
            AuthAccountRole accountRole = new AuthAccountRole();
            accountRole.setAccountId(accountId);
            accountRole.setRoleId(accessRoles.stream().filter(accessRole -> accessRole.getName().equals(role)).findFirst().orElseThrow().getId());
            return accountRole;
        }).toList();
        saveBatch(accountRoles);
    }

    @Override
    public void deleteByRoleId(Long roleId) {
        baseMapper.deleteByRoleId(roleId);
    }
}
