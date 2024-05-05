package org.zkit.support.server.account.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author generator
 * @since 2024-05-04
 */
@RestController
@RequestMapping("/api/account/auth/account")
@Slf4j
public class AuthAccountController {

    @GetMapping("/test")
    public String test() throws Exception {
        throw new Exception("test abc");
    }

}
