package org.zkit.support.server.account.access.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import lombok.Data;

/**
 * <p>
 * 权限
 * </p>
 *
 * @author generator
 * @since 2024-05-13
 */
@Data
@TableName("access_authority")
@Schema(name = "AccessAuthority", description = "权限")
public class AccessAuthority implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "上级ID")
    private Long parentId;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "权限码")
    private String value;

    @Schema(description = "排序")
    private Integer sort;
}
