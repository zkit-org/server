package org.zkit.support.server.assets.api.entity.response;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "预签名结果")
public class OSSPresignResponse implements Serializable {

    @Schema(description = "文件ID")
    private Long fileId;
    @Schema(description = "文件名")
    private String fileName;
    @Schema(description = "签名链接")
    private String signedUrl;
    @Schema(description = "访问链接")
    private String url;
}
