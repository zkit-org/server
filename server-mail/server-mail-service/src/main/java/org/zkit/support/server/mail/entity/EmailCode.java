package org.zkit.support.server.mail.entity;

import lombok.Data;

import java.util.Date;

@Data
public class EmailCode {

    private String code;
    private Date createdAt;

}
