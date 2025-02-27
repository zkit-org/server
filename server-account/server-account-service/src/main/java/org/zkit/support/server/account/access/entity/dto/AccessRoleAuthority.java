package org.zkit.support.server.account.access.entity.dto;

import com.baomidou.mybatisplus.extension.handlers.Fastjson2TypeHandler;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色包含的权限
 * </p>
 *
 * @author generator
 * @since 2025-02-27
 */
@Data
@Accessors(chain = true)
@TableName("access_role_authority")
@Schema(name = "AccessRoleAuthority", description = "角色包含的权限")
public class AccessRoleAuthority implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "角色ID")
    private Long roleId;

    @Schema(description = "权限ID")
    private Long authorityId;
}
