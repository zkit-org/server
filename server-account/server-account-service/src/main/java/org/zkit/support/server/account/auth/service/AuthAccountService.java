package org.zkit.support.server.account.auth.service;

import org.zkit.support.server.account.api.entity.AccountResponse;
import org.zkit.support.server.account.auth.entity.dto.AuthAccount;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author generator
 * @since 2024-05-04
 */
public interface AuthAccountService extends IService<AuthAccount> {

    AuthAccount test(String username);

}
