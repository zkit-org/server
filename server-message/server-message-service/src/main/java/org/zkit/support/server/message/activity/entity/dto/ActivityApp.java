package org.zkit.support.server.message.activity.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 应用
 * </p>
 *
 * @author generator
 * @since 2025-09-27
 */
@Data
@Accessors(chain = true)
@TableName("activity_app")
@Schema(name = "ActivityApp", description = "应用")
public class ActivityApp implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "应用标识")
    private String label;

    @Schema(description = "应用名")
    private String name;

    @Schema(description = "域名")
    private String baseUrl;
}
