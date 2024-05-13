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
 * 接口
 * </p>
 *
 * @author generator
 * @since 2024-05-13
 */
@Data
@TableName("access_api")
@Schema(name = "AccessApi", description = "接口")
public class AccessApi implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "请求路径")
    private String path;

    @Schema(description = "请求方法")
    private String method;
}
