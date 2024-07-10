package org.zkit.support.server.assets.oss.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zkit.support.server.assets.oss.entity.request.AliOSSPreSignRequest;
import org.zkit.support.server.assets.oss.entity.response.AliOSSPreSignResponse;
import org.zkit.support.server.assets.oss.service.OssFileService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author generator
 * @since 2024-07-10
 */
@RestController
@RequestMapping("/oss/file")
@Tag(name = "oss-file", description = "OSS文件")
public class OssFileController {

    @Resource
    private OssFileService ossFileService;

    @Operation(summary = "预签名")
    @PostMapping("/pre-sign")
    public AliOSSPreSignResponse preSign(@RequestBody() AliOSSPreSignRequest request) {
        return ossFileService.preSign(request);
    }

}
