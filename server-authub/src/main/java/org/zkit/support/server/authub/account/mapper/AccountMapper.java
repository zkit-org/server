package org.zkit.support.server.authub.account.mapper;

import java.util.List;

import org.zkit.support.server.authub.account.entity.dto.Account;
import org.zkit.support.server.authub.account.mapper.base.AccountBaseMapper;

/**
 * <p>
 * 账号 扩展 Mapper 接口
 * </p>
 *
 * @author generator
 * @since 2025-09-29
 */
public interface AccountMapper extends AccountBaseMapper {
    
    List<Account> selectAll();
    
}
