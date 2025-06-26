package org.zkit.support.server.ai.api.entity;

import java.io.Serializable;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "向量文档")
public class Document implements Serializable {

    @Schema(description = "内容")
    private String content;
    @Schema(description = "id")
    private String id;
    @Schema(description = "元数据")
    private Map<String, Object> metadata;

}
