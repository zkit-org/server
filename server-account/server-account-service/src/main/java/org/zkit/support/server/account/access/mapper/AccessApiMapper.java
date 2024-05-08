package org.zkit.support.server.account.access.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zkit.support.server.account.access.entity.dto.AccessApi;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author generator
 * @since 2024-05-04
 */
public interface AccessApiMapper extends BaseMapper<AccessApi> {

    List<AccessApi> findApisByAuthorityIds(@Param("authorityIds") List<Long> authorityIds);

}
