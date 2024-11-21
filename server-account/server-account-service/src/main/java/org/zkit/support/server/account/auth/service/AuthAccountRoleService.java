package org.zkit.support.server.account.auth.service;

import org.zkit.support.server.account.auth.entity.dto.AuthAccountRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 账号的角色 服务类
 * </p>
 *
 * @author generator
 * @since 2024-05-11
 */
public interface AuthAccountRoleService extends IService<AuthAccountRole> {

    void addRoles(Long accountId, List<String> roles);
    void deleteByRoleId(Long roleId);

}
