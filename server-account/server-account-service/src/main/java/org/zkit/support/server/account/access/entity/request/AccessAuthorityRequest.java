package org.zkit.support.server.account.access.entity.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "添加或编辑权限请求")
public class AccessAuthorityRequest {

    @Schema(description = "主键，编辑时必填")
    private Long id;

    @Schema(description = "上级ID")
    private Long parentId;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "权限码")
    private String value;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "API ID列表")
    private List<Long> apis;

    @Schema(description = "操作人", hidden = true)
    private Long actionUser;

}
