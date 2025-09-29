package org.zkit.support.server.authub.account.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 账号授权
 * </p>
 *
 * @author generator
 * @since 2025-09-29
 */
@Data
@Accessors(chain = true)
@TableName("account_token")
@Schema(name = "AccountToken", description = "账号授权")
public class AccountToken implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "账号ID")
    private Long accountId;

    @Schema(description = "应用ID")
    private Long appId;

    @Schema(description = "刷新令牌")
    private String refreshToken;

    @Schema(description = "访问令牌")
    private String accessToken;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;
}
