package org.zkit.support.server.account.access.service;

import org.zkit.support.server.account.access.entity.dto.AccessAuthorityApi;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限包含的接口 服务类
 * </p>
 *
 * @author generator
 * @since 2024-05-11
 */
public interface AccessAuthorityApiService extends IService<AccessAuthorityApi> {

    List<AccessAuthorityApi> findByAuthorityId(Long authorityId);
    void saveApis(Long authorityId, List<Long> apiIds);
    void deleteByAuthorityId(Long authorityId);
    void deleteByAuthorityIds(List<Long> authorityIds);

}
