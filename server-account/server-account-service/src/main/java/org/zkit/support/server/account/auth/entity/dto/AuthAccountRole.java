package org.zkit.support.server.account.auth.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import lombok.Data;

/**
 * <p>
 * 账号的角色
 * </p>
 *
 * @author generator
 * @since 2024-05-11
 */
@Data
@TableName("auth_account_role")
@Schema(name = "AuthAccountRole", description = "账号的角色")
public class AuthAccountRole implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "账号ID")
    private Long accountId;

    @Schema(description = "角色ID")
    private Long roleId;
}
