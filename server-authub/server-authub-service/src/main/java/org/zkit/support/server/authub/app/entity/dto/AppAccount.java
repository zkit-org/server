package org.zkit.support.server.authub.app.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 应用账号
 * </p>
 *
 * @author generator
 * @since 2025-10-01
 */
@Data
@Accessors(chain = true)
@TableName("app_account")
@Schema(name = "AppAccount", description = "应用账号")
public class AppAccount implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "应用ID")
    private Long appId;

    @Schema(description = "账号ID")
    private Long accountId;

    @Schema(description = "注册时间")
    private Date joinTime;
}
