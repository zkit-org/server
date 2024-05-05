package org.zkit.support.server.account.auth.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName("auth_app")
public class AuthApp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * App 名称
     */
    private String name;

    /**
     * ID
     */
    private String clientId;

    /**
     * 密钥
     */
    private String clientSecret;

    /**
     * 授权地址
     */
    private String authorizeUrl;

    /**
     * 获取 token 地址
     */
    private String tokenUrl;

    /**
     * 回调地址
     */
    private String callbackUrl;

    /**
     * 获取用户信息
     */
    private String infoUrl;
}
