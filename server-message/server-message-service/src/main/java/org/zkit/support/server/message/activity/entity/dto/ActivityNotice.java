package org.zkit.support.server.message.activity.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 通知
 * </p>
 *
 * @author generator
 * @since 2025-07-02
 */
@Data
@Accessors(chain = true)
@TableName("activity_notice")
@Schema(name = "ActivityNotice", description = "通知")
public class ActivityNotice implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "活动ID")
    private Long activityId;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "创建时间")
    private Date createdTime;

    @Schema(description = "是否已读")
    private Boolean read;

    @Schema(description = "更新时间")
    private Date updateTime;
}
