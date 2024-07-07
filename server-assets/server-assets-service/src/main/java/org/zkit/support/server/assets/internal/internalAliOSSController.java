package org.zkit.support.server.assets.internal;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zkit.support.server.assets.api.constant.AssetsApiRoute;
import org.zkit.support.server.assets.api.entity.request.OSSSignRequest;
import org.zkit.support.server.assets.service.AliOSSService;

@RestController
@Slf4j
@Tag(name = "internal-account", description = "[内部接口]阿里OSS")
public class internalAliOSSController {

    @Resource
    private AliOSSService aliOSSService;

    @Operation(summary = "预签名")
    @PostMapping(value = AssetsApiRoute.SIGN)
    public String sign(@RequestBody() OSSSignRequest request) {
        return aliOSSService.sign(request.getFileName(), request.getHeaders(), request.getMetadata());
    }

}
