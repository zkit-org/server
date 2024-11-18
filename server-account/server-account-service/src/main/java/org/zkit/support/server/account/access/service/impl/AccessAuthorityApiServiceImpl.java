package org.zkit.support.server.account.access.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.aop.framework.AopContext;
import org.zkit.support.server.account.access.entity.dto.AccessAuthorityApi;
import org.zkit.support.server.account.access.mapper.AccessAuthorityApiMapper;
import org.zkit.support.server.account.access.service.AccessAuthorityApiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

/**
 * <p>
 * 权限包含的接口 服务实现类
 * </p>
 *
 * @author generator
 * @since 2024-05-11
 */
@Service
public class AccessAuthorityApiServiceImpl extends ServiceImpl<AccessAuthorityApiMapper, AccessAuthorityApi> implements AccessAuthorityApiService {

    @Override
    public List<AccessAuthorityApi> findByAuthorityId(Long authorityId) {
        return baseMapper.findByAuthorityId(authorityId);
    }

    @Override
    @Transactional
    public void saveApis(Long authorityId, List<Long> apiIds) {
        if(apiIds != null && !apiIds.isEmpty()){
            Stream<AccessAuthorityApi> stream = apiIds.stream().map(apiId -> {
                AccessAuthorityApi accessAuthorityApi = new AccessAuthorityApi();
                accessAuthorityApi.setAuthorityId(authorityId);
                accessAuthorityApi.setApiId(apiId);
                return accessAuthorityApi;
            });
            AccessAuthorityApiService self = (AccessAuthorityApiService)AopContext.currentProxy();
            self.saveBatch(stream.toList());
        }
    }
}
