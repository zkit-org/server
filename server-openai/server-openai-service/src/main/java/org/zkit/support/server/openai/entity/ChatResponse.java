package org.zkit.support.server.openai.entity;

import lombok.Data;

import java.util.List;

@Data
public class ChatResponse {

    private List<Choice> choices;

}
