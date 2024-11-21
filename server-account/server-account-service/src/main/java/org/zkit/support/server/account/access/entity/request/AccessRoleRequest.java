package org.zkit.support.server.account.access.entity.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "创建/编辑角色请求")
public class AccessRoleRequest {

    @Schema(description = "ID", hidden = true)
    private Long id;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "是否启用")
    private Boolean enable;

    @Schema(description = "权限")
    private List<Long> authorities;

    @Schema(description = "操作人", hidden = true)
    private Long actionUser;

}
