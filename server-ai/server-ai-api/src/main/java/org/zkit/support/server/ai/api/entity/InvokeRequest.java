package org.zkit.support.server.ai.api.entity;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

import java.util.List;

@Data
public class InvokeRequest {

    private String content;
    private List<Message> messages;
    private JSONObject filter;
    private Boolean useVector = false;

}
