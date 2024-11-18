package org.zkit.support.server.account.access.entity.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.zkit.support.server.account.access.entity.request.AccessAuthorityRequest;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "权限详情")
public class AccessAuthorityResponse extends AccessAuthorityRequest {
}
