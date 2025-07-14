package org.zkit.support.server.message.api.entity.response;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson2.JSONObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Schema(description = "动态")
@Accessors(chain = true)
public class ActivityResponse implements Serializable {

    @Schema(description = "操作")
    private String action;

    @Schema(description = "地址")
    private String url;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "动态时间")
    private Date createTime;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "元数据")
    private JSONObject metadata;

    @Schema(description = "应用")
    private App app;

    @Data
    @Schema(description = "应用")
    @Accessors(chain = true)
    public static class App implements Serializable {

        @Schema(description = "应用ID")
        private String id;

        @Schema(description = "应用标识")
        private String label;

        @Schema(description = "应用名称")
        private String name;

        @Schema(description = "应用图标")
        private String baseUrl;
    }

}
