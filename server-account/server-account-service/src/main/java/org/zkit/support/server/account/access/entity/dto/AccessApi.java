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
 * 接口
 * </p>
 *
 * @author generator
 * @since 2025-02-27
 */
@Data
@Accessors(chain = true)
@TableName("access_api")
@Schema(name = "AccessApi", description = "接口")
public class AccessApi implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "请求路径")
    private String path;

    @Schema(description = "请求方法")
    private String method;
}
