package org.zkit.support.server.assets.api.entity.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
@Schema(description = "预签名")
public class OSSPresignRequest implements Serializable {

    @Schema(description = "文件名")
    private String fileName;
    @Schema(description = "请求头")
    private Map<String, String> headers;
    @Schema(description = "元数据")
    private Map<String, String> metadata;
    @Schema(description = "桶类型 0-私桶 1-公桶")
    private Integer type;
    @Schema(description = "用户ID", hidden = true)
    private Long userId;

}
