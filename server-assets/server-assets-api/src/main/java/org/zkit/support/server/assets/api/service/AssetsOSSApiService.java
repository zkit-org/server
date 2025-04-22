package org.zkit.support.server.assets.api.service;

import org.zkit.support.server.assets.api.entity.request.OSSSignRequest;

public interface AssetsOSSApiService {

    String sign(OSSSignRequest request);

}
