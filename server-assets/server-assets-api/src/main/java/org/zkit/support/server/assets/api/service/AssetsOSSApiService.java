package org.zkit.support.server.assets.api.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.zkit.support.server.assets.api.entity.request.OSSSignRequest;
import org.zkit.support.starter.boot.entity.Result;

public interface AssetsOSSApiService {

    String sign(OSSSignRequest request);

}
