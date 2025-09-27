package org.zkit.support.server.message.activity.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 操作项
 * </p>
 *
 * @author generator
 * @since 2025-09-27
 */
@Data
@Accessors(chain = true)
@TableName("activity_action")
@Schema(name = "ActivityAction", description = "操作项")
public class ActivityAction implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "应用ID")
    private Long appId;

    @Schema(description = "操作")
    private String action;

    @Schema(description = "备注")
    private String memo;
}
