package org.zkit.support.server.assets.alioss.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zkit.support.server.assets.alioss.service.AliOSSService;
import org.zkit.support.server.assets.api.entity.request.OSSSignRequest;

@RestController("/oss/ali")
@Slf4j
@Tag(name = "ali-oss", description = "阿里OSS")
public class AliOSSController {

    @Resource
    private AliOSSService aliOSSService;

    @Operation(summary = "预签名")
    @PostMapping("/pre-sign")
    public String preSign(@RequestBody() OSSSignRequest request) {
        return aliOSSService.sign(request.getFileName(), request.getHeaders(), request.getMetadata());
    }

}
