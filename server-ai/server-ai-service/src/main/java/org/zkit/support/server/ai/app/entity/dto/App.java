package org.zkit.support.server.ai.app.entity.dto;

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
@Schema(name = "App", description = "应用")
public class App implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "备注")
    private String memo;
}
