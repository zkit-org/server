package org.zkit.support.server.account.access.entity.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "查询角色请求")
public class AccessRoleQueryRequest {

    @Schema(description = "是否启用 0-否 1-是 2-全部")
    private String enable;

    @Schema(description = "是否启用", hidden = true)
    private Boolean enableBool;

    @Schema(description = "关键字", hidden = true)
    private String keyword;

}
