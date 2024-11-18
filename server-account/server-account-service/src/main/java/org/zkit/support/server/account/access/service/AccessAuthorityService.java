package org.zkit.support.server.account.access.service;

import org.zkit.support.server.account.access.entity.dto.AccessAuthority;
import com.baomidou.mybatisplus.extension.service.IService;
import org.zkit.support.server.account.access.entity.request.AccessAuthorityRequest;
import org.zkit.support.server.account.access.entity.response.AccessAuthorityResponse;
import org.zkit.support.server.account.access.entity.response.AccessAuthorityTreeResponse;

import java.util.List;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author generator
 * @since 2024-05-11
 */
public interface AccessAuthorityService extends IService<AccessAuthority> {

    List<AccessAuthorityTreeResponse> tree();
    void add(AccessAuthorityRequest request);
    AccessAuthorityResponse detail(Long id);

}
