package org.zkit.support.server.assets.oss.entity.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "结果")
public class AliOSSPreSignResponse {

    @Schema(description = "文件名")
    private String fileName;
    @Schema(description = "签名链接")
    private String signedUrl;
    @Schema(description = "访问链接")
    private String url;
}
