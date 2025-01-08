package org.zkit.support.server.ai.api.entity;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

import java.util.List;

@Data
public class InvokeHttpEntity {

    private String content;
    private List<Message> messages;
    private Integer limit;
    private JSONObject filter;
    private Boolean use_vector = false;

}
