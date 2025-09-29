package org.zkit.support.server.authub.app.entity.dto;

import java.io.Serializable;
import java.util.Date;
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
 * @since 2025-09-29
 */
@Data
@Accessors(chain = true)
@Schema(name = "App", description = "应用")
public class App implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "应用ID")
    private Long id;

    @Schema(description = "应用名称")
    private String name;

    @Schema(description = "说明")
    private String memo;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "是否启用")
    private Boolean enable;

    @Schema(description = "是否已删除")
    private Boolean deleted;

    @Schema(description = "创建人")
    private Long ownerId;

    @Schema(description = "LOGO")
    private String logo;

    @Schema(description = "测试字段")
    private String test;
}
