package org.zkit.support.server.account.access.entity.dto;

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
@TableName("access_authority_api")
public class AccessAuthorityApi implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 权限ID
     */
    private Integer authorityId;

    /**
     * 接口ID
     */
    private Integer apiId;
}
