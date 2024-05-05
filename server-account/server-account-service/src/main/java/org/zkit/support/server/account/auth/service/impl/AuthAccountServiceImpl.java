package org.zkit.support.server.account.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.zkit.support.server.account.api.entity.AccountResponse;
import org.zkit.support.server.account.auth.entity.dto.AuthAccount;
import org.zkit.support.server.account.auth.entity.mapstruct.AuthAccountMapStruct;
import org.zkit.support.server.account.auth.mapper.AuthAccountMapper;
import org.zkit.support.server.account.auth.service.AuthAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author generator
 * @since 2024-05-04
 */
@Service
public class AuthAccountServiceImpl extends ServiceImpl<AuthAccountMapper, AuthAccount> implements AuthAccountService {

    @Cacheable(value = "account", key = "#username")
    @Override
    public AuthAccount test(String username) {
        return baseMapper.findOneByUsername(username);
    }

}
