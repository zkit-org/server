package org.zkit.support.server.account.auth.entity.dto;

import com.baomidou.mybatisplus.extension.handlers.Fastjson2TypeHandler;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 三方用户绑定
 * </p>
 *
 * @author generator
 * @since 2025-04-22
 */
@SuppressWarnings("unused")
@Data
@Accessors(chain = true)
@TableName("auth_open_user")
@Schema(name = "AuthOpenUser", description = "三方用户绑定")
public class AuthOpenUser implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "用户唯一ID")
    private String openId;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "平台")
    private String platform;

    @Schema(description = "绑定账号 ID")
    private Long accountId;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "访问令牌")
    private String accessToken;

    @Schema(description = "令牌类型")
    private String tokenType;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;
}
