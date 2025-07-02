package org.zkit.support.server.message.api.entity.request;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson2.JSONObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Schema(description = "动态")
@Accessors(chain = true)
public class ActivityRequest implements Serializable {
    
    @Schema(description = "操作")
    private String action;

    @Schema(description = "应用标识")
    private String app;

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

    @Schema(description = "通知用户ID")
    private List<Long> noticeUserIds;

}
