package org.zkit.support.server.ai.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ChatResponse {

    private String id;
    private String object;
    private Date created;
    private String model;
    private List<ChatChoice> choices;
    private ChatUsage usage;

}
