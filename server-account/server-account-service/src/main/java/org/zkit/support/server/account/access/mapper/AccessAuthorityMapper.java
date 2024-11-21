package org.zkit.support.server.account.access.mapper;
import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zkit.support.server.account.access.entity.dto.AccessAuthority;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author generator
 * @since 2024-05-04
 */
public interface AccessAuthorityMapper extends BaseMapper<AccessAuthority> {

    List<AccessAuthority> findAuthoritiesByAccountId(@Param("accountId") Long accountId);
    int countByValue(@Param("value") String value);
    AccessAuthority findOneByValue(@Param("value") String value);
    List<AccessAuthority> findByIdIn(@Param("idList") Collection<Long> idList);

}
