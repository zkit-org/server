package org.zkit.support.server.account.auth.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author generator
 * @since 2024-05-04
 */
@Getter
@Setter
@TableName("auth_open_user")
public class AuthOpenUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户唯一ID
     */
    private String openId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 平台
     */
    private String platform;

    /**
     * 绑定账号 ID
     */
    private Integer accountId;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 访问令牌
     */
    private String accessToken;

    /**
     * 令牌类型
     */
    private String tokenType;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
