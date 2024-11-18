package org.zkit.support.server.account.access.service.impl;

import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.zkit.support.server.account.access.entity.dto.AccessAuthority;
import org.zkit.support.server.account.access.entity.dto.AccessAuthorityApi;
import org.zkit.support.server.account.access.entity.mapstruct.AccessAuthorityMapStruct;
import org.zkit.support.server.account.access.entity.request.AccessAuthorityRequest;
import org.zkit.support.server.account.access.entity.response.AccessAuthorityResponse;
import org.zkit.support.server.account.access.entity.response.AccessAuthorityTreeResponse;
import org.zkit.support.server.account.access.mapper.AccessAuthorityMapper;
import org.zkit.support.server.account.access.service.AccessAuthorityApiService;
import org.zkit.support.server.account.access.service.AccessAuthorityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.zkit.support.server.account.enums.AccountCode;
import org.zkit.support.starter.boot.exception.ResultException;
import org.zkit.support.starter.boot.utils.MessageUtils;
import org.zkit.support.starter.redisson.DistributedLock;

import java.util.List;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author generator
 * @since 2024-05-11
 */
@Service
public class AccessAuthorityServiceImpl extends ServiceImpl<AccessAuthorityMapper, AccessAuthority> implements AccessAuthorityService {

    @Resource
    private AccessAuthorityMapStruct mapStruct;
    @Resource
    private AccessAuthorityApiService accessAuthorityApiService;

    @Override
    @Cacheable(value = "access:authority", key = "'tree'")
    public List<AccessAuthorityTreeResponse> tree() {
        List<AccessAuthority> all = list();
        return all.stream()
                .filter(accessAuthority -> accessAuthority.getParentId() == null)
                .map(accessAuthority -> {
                    AccessAuthorityTreeResponse response = mapStruct.toAccessAuthorityTreeResponse(accessAuthority);
                    response.setChildren(findChildren(all, accessAuthority.getId()));
                    return response;
                })
                .toList();
    }

    private List<AccessAuthorityTreeResponse> findChildren(List<AccessAuthority> all, Long parentId) {
        return all.stream()
                .filter(accessAuthority -> parentId.equals(accessAuthority.getParentId()))
                .map(accessAuthority -> {
                    AccessAuthorityTreeResponse response = mapStruct.toAccessAuthorityTreeResponse(accessAuthority);
                    response.setChildren(findChildren(all, accessAuthority.getId()));
                    return response;
                })
                .toList();
    }

    @Override
    @DistributedLock(value = "access:authority:add", el = false)
    @CacheEvict(value = "access:authority", key = "'tree'")
    @Transactional
    public void add(AccessAuthorityRequest request) {
        int size = baseMapper.countByValue(request.getValue());
        if(size > 0)
            throw new ResultException(AccountCode.ACCESS_AUTHORITY_ADD_EXIST.code, MessageUtils.get(AccountCode.ACCESS_AUTHORITY_ADD_EXIST.key));
        AccessAuthority aa = mapStruct.fromAccessAuthorityRequest(request);
        aa.setCreateUser(request.getActionUser());
        aa.setUpdateUser(request.getActionUser());
        save(aa);
        accessAuthorityApiService.saveApis(aa.getId(), request.getApis());
    }

    @Override
    public AccessAuthorityResponse detail(Long id) {
        AccessAuthority accessAuthority = getById(id);
        AccessAuthorityResponse response = mapStruct.toAccessAuthorityResponse(accessAuthority);
        List<AccessAuthorityApi> apis = accessAuthorityApiService.findByAuthorityId(id);
        List<Long> apiIds = apis.stream().map(AccessAuthorityApi::getApiId).toList();
        response.setApis(apiIds);
        return response;
    }
}
