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
 * 权限包含的接口
 * </p>
 *
 * @author generator
 * @since 2024-05-11
 */
@Data
@TableName("access_authority_api")
@Schema(name = "AccessAuthorityApi", description = "权限包含的接口")
public class AccessAuthorityApi implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "权限ID")
    private Long authorityId;

    @Schema(description = "接口ID")
    private Long apiId;
}
