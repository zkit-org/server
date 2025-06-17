package org.zkit.support.server.assets.oss.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zkit.support.server.assets.api.entity.request.OSSPresignRequest;
import org.zkit.support.server.assets.api.entity.response.OSSPresignResponse;
import org.zkit.support.server.assets.oss.service.OssFileService;
import org.zkit.support.starter.security.annotation.CurrentUser;
import org.zkit.support.starter.security.entity.SessionUser;

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
@Tag(name = "OssFileController", description = "OSS文件")
public class OssFileController {

    @Resource
    private OssFileService ossFileService;

    @Operation(summary = "预签名")
    @PostMapping("/pre-sign")
    public OSSPresignResponse preSign(
            @CurrentUser() @Parameter(hidden = true) SessionUser user,
            @RequestBody() OSSPresignRequest request
    ) {
        request.setUserId(user.getId());
        return ossFileService.presign(request);
    }

}
