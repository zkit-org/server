package org.zkit.support.server.account.access.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.zkit.support.server.account.access.entity.dto.AccessRole;
import org.zkit.support.server.account.access.entity.request.AccessRoleQueryRequest;
import org.zkit.support.server.account.access.mapper.AccessRoleMapper;
import org.zkit.support.server.account.access.service.AccessRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.zkit.support.starter.mybatis.entity.PageQueryRequest;
import org.zkit.support.starter.mybatis.entity.PageResult;

import java.util.List;
import java.util.Map;

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

    @Override
    public PageResult<AccessRole> query(PageQueryRequest pq, AccessRoleQueryRequest query) {
        Boolean enable = null;
        switch (query.getEnable()) {
            case "0" -> enable = false;
            case "1" -> enable = true;
        }
        query.setEnableBool(enable);
        query.setKeyword(pq.getKeyword());
        Page<Module> page = pq.toPage();
        List<AccessRole> roles = baseMapper.query(page, query);
        return PageResult.of(page.getTotal(), roles);
    }
}
