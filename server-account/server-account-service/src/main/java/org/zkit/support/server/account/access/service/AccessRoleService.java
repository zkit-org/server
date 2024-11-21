package org.zkit.support.server.account.access.service;

import org.zkit.support.server.account.access.entity.dto.AccessRole;
import com.baomidou.mybatisplus.extension.service.IService;
import org.zkit.support.server.account.access.entity.request.AccessRoleRequest;
import org.zkit.support.server.account.access.entity.request.AccessRoleQueryRequest;
import org.zkit.support.server.account.access.entity.response.AccessRoleResponse;
import org.zkit.support.starter.mybatis.entity.PageQueryRequest;
import org.zkit.support.starter.mybatis.entity.PageResult;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author generator
 * @since 2024-05-04
 */
public interface AccessRoleService extends IService<AccessRole> {

    PageResult<AccessRole> query(PageQueryRequest page, AccessRoleQueryRequest query);
    void create(AccessRoleRequest request);
    List<AccessRole> findByNameIn(List<String> names);
    AccessRoleResponse detail(Long id);
    void update(AccessRoleRequest request);
    void disable(Long id, Long userId);
    void enable(Long id, Long userId);
    void deleteById(Long id);

}
