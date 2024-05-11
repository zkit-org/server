package org.zkit.support.server.account.auth.mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.zkit.support.server.account.auth.entity.dto.AuthAccount;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author generator
 * @since 2024-05-04
 */
public interface AuthAccountMapper extends BaseMapper<AuthAccount> {

    AuthAccount findOneByUsername(@Param("username") String username);

}
