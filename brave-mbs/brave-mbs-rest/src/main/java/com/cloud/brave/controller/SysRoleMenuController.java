package com.cloud.brave.controller;


import com.cloud.brave.core.result.Result;
import com.cloud.brave.service.SysRoleMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.cloud.brave.core.base.controller.BaseController;

/**
 * <p>
 * 角色资源关联信息表 前端控制器
 * </p>
 *
 * @author yongchen
 * @since 2021-06-18
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/sysRoleMenu")
@Api(value = "SysRoleMenuController", tags = "角色资源控制器")
public class SysRoleMenuController extends BaseController {

    private final SysRoleMenuService sysRoleMenuService;

    /**
     * @description: 刷新权限缓存
     * @param:
     * @return: com.cloud.brave.core.result.Result<java.lang.Boolean>
     * @author yongchen
     * @date: 2021/8/13 14:28
     */
    @GetMapping("/refreshCache")
    @ApiOperation(value = "刷新权限缓存", notes = "刷新权限缓存")
    public Result<Boolean> refreshCache(){
        sysRoleMenuService.getRolePerms();
        return success(true);
    }
}
