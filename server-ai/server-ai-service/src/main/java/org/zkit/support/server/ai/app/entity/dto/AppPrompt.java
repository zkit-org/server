package org.zkit.support.server.ai.app.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 应用提示词
 * </p>
 *
 * @author generator
 * @since 2025-09-27
 */
@Data
@Accessors(chain = true)
@TableName("app_prompt")
@Schema(name = "AppPrompt", description = "应用提示词")
public class AppPrompt implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "应用ID")
    private Long appId;

    @Schema(description = "功能")
    private String action;

    @Schema(description = "提示词模板")
    private String template;

    @Schema(description = "更新时间")
    private Date updateTime;
}
