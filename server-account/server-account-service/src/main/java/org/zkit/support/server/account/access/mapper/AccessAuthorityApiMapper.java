package org.zkit.support.server.account.access.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import org.zkit.support.server.account.access.entity.dto.AccessAuthorityApi;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 权限包含的接口 Mapper 接口
 * </p>
 *
 * @author generator
 * @since 2024-05-11
 */
public interface AccessAuthorityApiMapper extends BaseMapper<AccessAuthorityApi> {

    List<AccessAuthorityApi> findByAuthorityId(@Param("authorityId") Long authorityId);

}
