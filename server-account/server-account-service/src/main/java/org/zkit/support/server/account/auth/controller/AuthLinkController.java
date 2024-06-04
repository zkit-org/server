package org.zkit.support.server.account.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.zkit.support.server.account.api.entity.response.TokenResponse;
import org.zkit.support.server.account.auth.entity.request.AuthLinkBindRequest;
import org.zkit.support.server.account.auth.entity.response.AuthOpenUserResponse;
import org.zkit.support.server.account.auth.service.AuthAppService;
import org.zkit.support.starter.security.annotation.PublicRequest;

import java.io.IOException;

@RestController
@RequestMapping("/auth/link")
@Tag(name = "auth-link", description = "链接外部账户")
public class AuthLinkController {

    @Resource
    private AuthAppService authAppService;

    @PublicRequest
    @Operation(summary = "跳转第三方")
    @GetMapping("/{type}")
    public void type(
            HttpServletResponse response,
            @Parameter(description = "类型") @PathVariable("type") String type
    ) throws IOException {
        authAppService.redirect(response, type);
    }

    @PublicRequest
    @Operation(summary = "验证临时授权")
    @GetMapping("/{type}/code")
    public AuthOpenUserResponse code(
            @Parameter(description = "类型") @PathVariable("type") String type,
            @Parameter(description = "临时请求码") @RequestParam("code") String code
    ) {
        return authAppService.info(type, code);
    }

    @PublicRequest
    @Operation(summary = "验证临时授权")
    @PostMapping("/bind")
    public TokenResponse bind(@RequestBody AuthLinkBindRequest request) {
        return authAppService.bind(request);
    }

}
