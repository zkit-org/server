package org.zkit.support.server.account.access.entity.dto;

import com.baomidou.mybatisplus.extension.handlers.Fastjson2TypeHandler;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import lombok.Data;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author generator
 * @since 2024-06-05
 */
@Data
@TableName("access_role")
@Schema(name = "AccessRole", description = "角色")
public class AccessRole implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "角色名称")
    private String name;

    @Schema(description = "备注")
    private String description;

    @Schema(description = "是否启用")
    private Boolean enable;
}
