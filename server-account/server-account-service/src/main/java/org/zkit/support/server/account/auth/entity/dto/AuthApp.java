package org.zkit.support.server.account.auth.entity.dto;

import com.baomidou.mybatisplus.extension.handlers.Fastjson2TypeHandler;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 第三方应用
 * </p>
 *
 * @author generator
 * @since 2025-02-27
 */
@Data
@Accessors(chain = true)
@TableName("auth_app")
@Schema(name = "AuthApp", description = "第三方应用")
public class AuthApp implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "App 名称")
    private String name;

    @Schema(description = "ID")
    private String clientId;

    @Schema(description = "密钥")
    private String clientSecret;

    @Schema(description = "授权地址")
    private String authorizeUrl;

    @Schema(description = "获取 token 地址")
    private String tokenUrl;

    @Schema(description = "回调地址")
    private String callbackUrl;

    @Schema(description = "获取用户信息")
    private String infoUrl;
}
