package org.zkit.support.server.account.auth.mapper;
import org.apache.ibatis.annotations.Param;

import org.zkit.support.server.account.auth.entity.dto.AuthOpenUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 三方用户绑定 Mapper 接口
 * </p>
 *
 * @author generator
 * @since 2024-05-11
 */
public interface AuthOpenUserMapper extends BaseMapper<AuthOpenUser> {

    AuthOpenUser findOneByPlatformAndOpenId(@Param("platform") String platform, @Param("openId") String openId);
    int updateAccountIdById(@Param("accountId") Long accountId, @Param("id") Long id);

}
