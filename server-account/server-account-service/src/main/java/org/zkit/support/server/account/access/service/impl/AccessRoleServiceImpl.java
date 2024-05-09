package org.zkit.support.server.account.access.service.impl;

import org.zkit.support.server.account.access.entity.dto.AccessRole;
import org.zkit.support.server.account.access.mapper.AccessRoleMapper;
import org.zkit.support.server.account.access.service.AccessRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class AccessRoleServiceImpl extends ServiceImpl<AccessRoleMapper, AccessRole> implements AccessRoleService {
    @Override
    public void addRoles(Long accountId, List<String> roles) {

    }
}
