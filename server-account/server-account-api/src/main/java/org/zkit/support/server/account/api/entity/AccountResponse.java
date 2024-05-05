package org.zkit.support.server.account.api.entity;

import lombok.Data;

import java.util.Date;

@Data
public class AccountResponse {

    private String username;
    private Date createTime;

}
