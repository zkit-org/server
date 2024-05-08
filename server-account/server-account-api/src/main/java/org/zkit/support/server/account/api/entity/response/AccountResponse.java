package org.zkit.support.server.account.api.entity.response;

import lombok.Data;

import java.util.Date;

@Data
public class AccountResponse {

    private Long id;
    private String username;
    private Date createTime;
    private Integer status;
    private Integer otpStatus;
    private Boolean deleted;

}
