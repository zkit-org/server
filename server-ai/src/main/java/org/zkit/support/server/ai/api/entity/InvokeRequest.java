package org.zkit.support.server.ai.api.entity;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

@Data
public class InvokeRequest {

    private String content;
    private JSONObject filter;

}
