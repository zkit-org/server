package org.zkit.support.server.assets.api.service;

import org.zkit.support.server.assets.api.entity.request.OSSPresignRequest;
import org.zkit.support.server.assets.api.entity.response.OSSPresignResponse;

public interface AssetsOSSApiService {

    OSSPresignResponse presign(OSSPresignRequest request);
    String sign(String url);

}
