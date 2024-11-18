package org.zkit.support.server.account.access.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zkit.support.server.account.access.entity.dto.AccessRole;
import org.zkit.support.server.account.access.entity.request.AccessRoleQueryRequest;
import org.zkit.support.server.account.access.service.AccessRoleService;
import org.zkit.support.starter.mybatis.entity.PageQueryRequest;
import org.zkit.support.starter.mybatis.entity.PageResult;
import org.zkit.support.starter.security.annotation.CurrentUser;
import org.zkit.support.starter.security.entity.SessionUser;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author generator
 * @since 2024-05-11
 */
@RestController
@RequestMapping("/access/role")
@Tag(name = "role", description = "角色管理")
@Slf4j
public class AccessRoleController {

    @Resource
    private AccessRoleService accessRoleService;

    @GetMapping("/list")
    @Operation(summary = "列表")
    public PageResult<AccessRole> list(
            @ParameterObject @ModelAttribute PageQueryRequest page,
            @ParameterObject @ModelAttribute AccessRoleQueryRequest query,
            @CurrentUser @Parameter(hidden = true) SessionUser user
    ) {
        return accessRoleService.query(page, query);
    }

}
