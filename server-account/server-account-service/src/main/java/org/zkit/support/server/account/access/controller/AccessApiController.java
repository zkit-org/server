package org.zkit.support.server.account.access.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zkit.support.server.account.access.entity.dto.AccessApi;
import org.zkit.support.server.account.access.service.AccessApiService;

import java.util.List;

/**
 * <p>
 * 接口 前端控制器
 * </p>
 *
 * @author generator
 * @since 2024-05-11
 */
@RestController
@RequestMapping("/access/api")
@Tag(name = "AccessApiController", description = "接口管理")
@Slf4j
public class AccessApiController {

    @Resource
    private AccessApiService accessApiService;

    @GetMapping("/list")
    @Operation(summary = "列表")
    public List<AccessApi> list() {
        return accessApiService.list();
    }

}
