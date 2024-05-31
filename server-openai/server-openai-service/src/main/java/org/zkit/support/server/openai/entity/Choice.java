package org.zkit.support.server.openai.entity;

import lombok.Data;

@Data
public class Choice {

    private int index;
    private Message message;

}
