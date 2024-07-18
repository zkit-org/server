package org.zkit.support.server.ai.entity;

import lombok.Data;

@Data
public class ChatUsage {

    private String prompt_tokens;
    private String completion_tokens;
    private String total_tokens;
}
