package org.zkit.support.server.authub.account.entity.dto;

import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 账号
 * </p>
 *
 * @author generator
 * @since 2025-10-01
 */
@Data
@Accessors(chain = true)
@Schema(name = "Account", description = "账号")
public class Account implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "最后活动时间")
    private Date lastTime;

    @Schema(description = "是否已删除")
    private Boolean deleted;

    @Schema(description = "是否启用")
    private Boolean enable;
}
