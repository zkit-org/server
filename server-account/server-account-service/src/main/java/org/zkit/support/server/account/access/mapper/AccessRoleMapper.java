package org.zkit.support.server.account.access.mapper;
import java.util.Collection;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.zkit.support.server.account.access.entity.dto.AccessRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.zkit.support.server.account.access.entity.request.AccessRoleQueryRequest;

import java.util.List;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author generator
 * @since 2024-05-11
 */
public interface AccessRoleMapper extends BaseMapper<AccessRole> {

    List<AccessRole> query(@Param("query") AccessRoleQueryRequest query);
    List<AccessRole> findByNameIn(@Param("nameList") Collection<String> nameList);
    int countByNameAndEnable(@Param("name") String name, @Param("enable") Boolean enable);
    AccessRole findOneByNameAndEnable(@Param("name") String name, @Param("enable") Boolean enable);

}
