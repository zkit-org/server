package org.zkit.support.server.account.api.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTokenRequest {

    private Long id;
    private Long expiresIn;

}
