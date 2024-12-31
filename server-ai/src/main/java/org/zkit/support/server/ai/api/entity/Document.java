package org.zkit.support.server.ai.api.entity;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

@Data
public class Document {

    private String page_content;
    private String id;
    private JSONObject metadata;

}
