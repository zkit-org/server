package org.zkit.support.server.account.access.service;

import org.zkit.support.server.account.access.entity.dto.AccessRole;
import com.baomidou.mybatisplus.extension.service.IService;

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

    void addRoles(Long accountId, List<String> roles);

}
