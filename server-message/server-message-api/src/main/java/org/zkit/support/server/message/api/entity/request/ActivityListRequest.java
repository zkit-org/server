package org.zkit.support.server.message.api.entity.request;

import java.io.Serializable;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Schema(description = "动态列表请求")
@Accessors(chain = true)
public class ActivityListRequest implements Serializable {

    @Schema(description = "应用标识")
    private String app;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "元数据")
    private Map<String, Object> metadata;
    
    @Schema(description = "页码")
    private Integer page;

    @Schema(description = "每页条数")
    private Integer size;

}
