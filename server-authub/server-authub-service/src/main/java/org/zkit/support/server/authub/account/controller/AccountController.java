package org.zkit.support.server.authub.account.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zkit.support.starter.security.annotation.CurrentUser;
import org.zkit.support.starter.security.entity.SessionUser;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

/**
 * <p>
 * 账号 前端控制器
 * </p>
 *
 * @author generator
 * @since 2025-09-29
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @GetMapping("/profile")
    @Operation(summary = "当前用户信息")
    public SessionUser profile(@CurrentUser() @Parameter(hidden = true) SessionUser user) {
        return user;
    }

}
