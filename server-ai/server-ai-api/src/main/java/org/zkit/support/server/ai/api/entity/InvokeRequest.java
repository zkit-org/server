package org.zkit.support.server.ai.api.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "调用请求")
public class InvokeRequest implements Serializable {

    @Schema(description = "消息")
    private String message;

    @Schema(description = "规则")
    private List<String> rules;

    @Schema(description = "元数据")
    private Map<String, String> metadata;

    @Schema(description = "topK")
    private Integer topK;

    @Schema(description = "是否使用上下文")
    private Boolean useContext = false;

}
