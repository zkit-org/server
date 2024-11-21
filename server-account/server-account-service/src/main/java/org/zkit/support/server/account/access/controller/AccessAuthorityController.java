package org.zkit.support.server.account.access.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.zkit.support.server.account.access.entity.request.AccessAuthorityRequest;
import org.zkit.support.server.account.access.entity.response.AccessAuthorityResponse;
import org.zkit.support.server.account.access.entity.response.AccessAuthorityTreeResponse;
import org.zkit.support.server.account.access.service.AccessAuthorityService;
import org.zkit.support.starter.security.annotation.CurrentUser;
import org.zkit.support.starter.security.entity.SessionUser;

import java.util.List;

/**
 * <p>
 * 权限 前端控制器
 * </p>
 *
 * @author generator
 * @since 2024-05-11
 */
@RestController
@RequestMapping("/access/authority")
@Tag(name = "authority", description = "权限管理")
@Slf4j
public class AccessAuthorityController {

    @Resource
    private AccessAuthorityService accessAuthorityService;

    @GetMapping("/tree")
    @Operation(summary = "权限树")
    public List<AccessAuthorityTreeResponse> tree() {
        return accessAuthorityService.tree();
    }

    @PostMapping("/add")
    @Operation(summary = "添加权限")
    public void add(
            @Valid @RequestBody AccessAuthorityRequest request,
            @CurrentUser @Parameter(hidden = true) SessionUser user
    ) {
        request.setActionUser(user.getId());
        accessAuthorityService.add(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "权限详情")
    public AccessAuthorityResponse detail(@Parameter(description = "权限ID") @PathVariable Long id) {
        return accessAuthorityService.detail(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新权限")
    public void update(
            @Valid @RequestBody AccessAuthorityRequest request,
            @Parameter(description = "权限ID") @PathVariable Long id,
            @CurrentUser @Parameter(hidden = true) SessionUser user
    ) {
        request.setActionUser(user.getId());
        request.setId(id);
        accessAuthorityService.update(request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除权限")
    public void delete(
            @Parameter(description = "权限ID") @PathVariable Long id,
            @CurrentUser @Parameter(hidden = true) SessionUser user
    ) {
        accessAuthorityService.deleteById(id);
    }

}
