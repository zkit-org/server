package org.zkit.support.server.account.access.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.Page;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.zkit.support.server.account.access.entity.dto.AccessRole;
import org.zkit.support.server.account.access.entity.mapstruct.AccessRoleMapStruct;
import org.zkit.support.server.account.access.entity.request.AccessRoleRequest;
import org.zkit.support.server.account.access.entity.request.AccessRoleQueryRequest;
import org.zkit.support.server.account.access.entity.response.AccessAuthorityTreeResponse;
import org.zkit.support.server.account.access.entity.response.AccessRoleResponse;
import org.zkit.support.server.account.access.mapper.AccessRoleMapper;
import org.zkit.support.server.account.access.service.AccessRoleAuthorityService;
import org.zkit.support.server.account.access.service.AccessRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.zkit.support.server.account.auth.service.AuthAccountRoleService;
import org.zkit.support.server.account.enums.AccountCode;
import org.zkit.support.starter.boot.exception.ResultException;
import org.zkit.support.starter.boot.utils.MessageUtils;
import org.zkit.support.starter.mybatis.entity.PageRequest;
import org.zkit.support.starter.mybatis.entity.PageResult;
import org.zkit.support.starter.redisson.DistributedLock;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author generator
 * @since 2024-05-04
 */
@Service
@Slf4j
public class AccessRoleServiceImpl extends ServiceImpl<AccessRoleMapper, AccessRole> implements AccessRoleService {

    @Resource
    private AccessRoleMapStruct mapStruct;
    @Resource
    private AccessRoleAuthorityService accessRoleAuthorityService;
    @Resource
    @Lazy
    private AuthAccountRoleService authAccountRoleService;

    @Override
    public PageResult<AccessRole> query(PageRequest pq, AccessRoleQueryRequest query) {
        Boolean enable = null;
        switch (query.getEnable()) {
            case "0" -> enable = false;
            case "1" -> enable = true;
        }
        query.setEnableBool(enable);
        query.setKeyword(pq.getKeyword());
        Page<AccessRole> page = pq.start();
        baseMapper.query(query);
        return PageResult.of(page);
    }

    @Override
    @DistributedLock(value = "access:role:add", el = false)
    @Transactional
    public void create(AccessRoleRequest request) {
        int size = baseMapper.countByNameAndEnable(request.getName(), true);
        if(size > 0) {
            throw new ResultException(AccountCode.ACCESS_ROLE_EXIST.code, MessageUtils.get(AccountCode.ACCESS_ROLE_EXIST.key));
        }
        AccessRole ar = mapStruct.toAccessRole(request);
        ar.setCreateUser(request.getActionUser());
        ar.setUpdateUser(request.getActionUser());
        save(ar);
        accessRoleAuthorityService.saveAuthorities(ar.getId(), request.getAuthorities());
    }

    @Override
    public List<AccessRole> findByNameIn(List<String> names) {
        return baseMapper.findByNameIn(names);
    }

    @Override
    @Cacheable(value = "access:role", key = "#id")
    public AccessRoleResponse detail(Long id) {
        AccessRole role = getById(id);
        AccessRoleResponse response = mapStruct.toAccessRoleResponse(role);
        List<AccessAuthorityTreeResponse> tree = accessRoleAuthorityService.tree(id);
        response.setAuthorityTree(tree);
        response.setAuthorities(accessRoleAuthorityService.authorityIds(id));
        return response;
    }

    @Override
    @DistributedLock(value = "'access:role:' + #request.id")
    @Transactional
    @CacheEvict(value = "access:role", key = "#request.id")
    public void update(AccessRoleRequest request) {
        AccessRole role = baseMapper.findOneByNameAndEnable(request.getName(), true);
        if(role != null && !role.getId().equals(request.getId())) {
            throw new ResultException(AccountCode.ACCESS_ROLE_EXIST.code, MessageUtils.get(AccountCode.ACCESS_ROLE_EXIST.key));
        }
        AccessRole ar = mapStruct.toAccessRole(request);
        ar.setId(request.getId());
        ar.setUpdateUser(request.getActionUser());
        updateById(ar);
        accessRoleAuthorityService.saveAuthorities(ar.getId(), request.getAuthorities());
    }

    @Override
    @DistributedLock(value = "'access:role:' + #id")
    @Transactional
    @CacheEvict(value = "access:role", key = "#id")
    public void disable(Long id, Long userId) {
        update(Wrappers.lambdaUpdate(AccessRole.class)
                .eq(AccessRole::getId, id)
                .set(AccessRole::getEnable, false)
                .set(AccessRole::getUpdateUser, userId));
    }

    @Override
    @DistributedLock(value = "'access:role:' + #id")
    @Transactional
    @CacheEvict(value = "access:role", key = "#id")
    public void enable(Long id, Long userId) {
        update(Wrappers.lambdaUpdate(AccessRole.class)
                .eq(AccessRole::getId, id)
                .set(AccessRole::getEnable, true)
                .set(AccessRole::getUpdateUser, userId));
    }

    @Override
    @DistributedLock(value = "'access:role:' + #id")
    @Transactional
    @CacheEvict(value = "access:role", key = "#id")
    public void deleteById(Long id) {
        accessRoleAuthorityService.deleteByRoleId(id);
        authAccountRoleService.deleteByRoleId(id);
        removeById(id);
    }
}
