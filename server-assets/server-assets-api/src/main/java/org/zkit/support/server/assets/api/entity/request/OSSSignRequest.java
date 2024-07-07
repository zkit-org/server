package org.zkit.support.server.assets.api.entity.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Map;

@Data
@Schema(description = "预签名")
public class OSSSignRequest {

    @Schema(description = "文件名")
    private String fileName;
    @Schema(description = "请求头")
    private Map<String, String> headers;
    @Schema(description = "元数据")
    private Map<String, String> metadata;

}
