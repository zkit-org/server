package org.zkit.support.server.account.access.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import org.zkit.support.server.account.access.entity.dto.AccessRole;
import org.zkit.support.server.account.access.entity.request.AccessRoleRequest;
import org.zkit.support.server.account.access.entity.request.AccessRoleQueryRequest;
import org.zkit.support.server.account.access.entity.response.AccessRoleResponse;
import org.zkit.support.server.account.access.service.AccessRoleService;
import org.zkit.support.starter.mybatis.entity.PageRequest;
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
            @ParameterObject @ModelAttribute PageRequest page,
            @ParameterObject @ModelAttribute AccessRoleQueryRequest query,
            @CurrentUser @Parameter(hidden = true) SessionUser user
    ) {
        return accessRoleService.query(page, query);
    }

    @PostMapping("/create")
    @Operation(summary = "创建")
    public void create(
            @Valid @RequestBody AccessRoleRequest request,
            @CurrentUser @Parameter(hidden = true) SessionUser user
    ) {
        request.setActionUser(user.getId());
        accessRoleService.create(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "详情")
    public AccessRoleResponse detail(@Parameter(description = "角色ID") @PathVariable Long id) {
        return accessRoleService.detail(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新")
    public void update(
            @Valid @RequestBody AccessRoleRequest request,
            @Parameter(description = "角色ID") @PathVariable Long id,
            @CurrentUser @Parameter(hidden = true) SessionUser user
    ) {
        request.setId(id);
        request.setActionUser(user.getId());
        accessRoleService.update(request);
    }

    @PutMapping("/{id}/disable")
    @Operation(summary = "禁用")
    public void disable(
            @Parameter(description = "角色ID") @PathVariable Long id,
            @CurrentUser @Parameter(hidden = true) SessionUser user
    ) {
        accessRoleService.disable(id, user.getId());
    }

    @PutMapping("/{id}/enable")
    @Operation(summary = "启用")
    public void enable(
            @Parameter(description = "角色ID") @PathVariable Long id,
            @CurrentUser @Parameter(hidden = true) SessionUser user
    ) {
        accessRoleService.enable(id, user.getId());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除")
    public void delete(@Parameter(description = "角色ID") @PathVariable Long id) {
        accessRoleService.deleteById(id);
    }

}
