package org.zkit.support.server.account.access.entity.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.zkit.support.server.account.access.entity.dto.AccessAuthority;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "权限树")
public class AccessAuthorityTreeResponse extends AccessAuthority {

    @Schema(description = "子节点")
    private List<AccessAuthorityTreeResponse> children;

}
