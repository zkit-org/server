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
 * 账号
 * </p>
 *
 * @author generator
 * @since 2025-02-27
 */
@Data
@Accessors(chain = true)
@TableName("auth_account")
@Schema(name = "AuthAccount", description = "账号")
public class AuthAccount implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "状态: 0 未激活 1 正常 2 禁用")
    private Integer status;

    @Schema(description = "是否已删除")
    private Boolean deleted;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "OTP密钥")
    private String otpSecret;

    @Schema(description = "OTP绑定状态: 0 未绑定 1 已绑定")
    private Integer otpStatus;

    @Schema(description = "OTP生效时间")
    private Date otpEnableTime;
}
