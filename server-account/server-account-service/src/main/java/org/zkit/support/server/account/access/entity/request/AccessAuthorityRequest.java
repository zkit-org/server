package org.zkit.support.server.account.access.entity.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "添加或编辑权限请求")
public class AccessAuthorityRequest {

    @Schema(description = "主键，编辑时必填")
    private Long id;

    @Schema(description = "上级ID")
    private Long parentId;

    @NotNull
    @Schema(description = "名称")
    private String name;

    @NotNull
    @Schema(description = "权限码")
    @Pattern(regexp = "^[a-z0-9]+(?::[a-z0-9]+)*$", message = "{access.authority.value.pattern}")
    private String value;

    @NotNull
    @Min(0)
    @Max(999)
    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "API ID列表")
    private List<Long> apis;

    @Schema(description = "操作人", hidden = true)
    private Long actionUser;

}
