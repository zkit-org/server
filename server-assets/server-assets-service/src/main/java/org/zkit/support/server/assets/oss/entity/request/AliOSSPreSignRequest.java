package org.zkit.support.server.assets.oss.entity.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "预签名")
public class AliOSSPreSignRequest {

    @Schema(description = "文件名")
    private String fileName;
    @Schema(description = "文件大小")
    private Long length;
    @Schema(description = "访问类型 0-私桶 1-公桶")
    private Integer type;
}
