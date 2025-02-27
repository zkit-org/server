package org.zkit.support.server.account.access.entity.dto;

import com.baomidou.mybatisplus.extension.handlers.Fastjson2TypeHandler;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限
 * </p>
 *
 * @author generator
 * @since 2025-02-27
 */
@Data
@Accessors(chain = true)
@TableName("access_authority")
@Schema(name = "AccessAuthority", description = "权限")
public class AccessAuthority implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "上级ID")
    private Long parentId;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "权限码")
    private String value;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "创建人")
    private Long createUser;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新人")
    private Long updateUser;

    @Schema(description = "更新时间")
    private Date updateTime;
}
