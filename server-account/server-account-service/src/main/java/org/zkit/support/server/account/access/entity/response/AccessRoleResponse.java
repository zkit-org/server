package org.zkit.support.server.account.access.entity.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "角色响应")
public class AccessRoleResponse {

    @Schema(description = "名称")
    private String name;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "是否启用")
    private Boolean enable;

    @Schema(description = "权限")
    private List<Long> authorities;

    @Schema(description = "权限树")
    private List<AccessAuthorityTreeResponse> authorityTree;

}
