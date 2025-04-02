package org.zkit.support.server.account.api.entity.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "账户添加请求")
public class AccountAddRequest implements Serializable {

    @Schema(description = "用户名")
    private String username;

}
