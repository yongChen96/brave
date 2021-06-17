package com.cloud.brave.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.brave.entity.SysRole;
import com.cloud.brave.service.SysRoleService;
import com.cloud.core.mybatisplus.page.PageParam;
import com.cloud.core.result.Result;
import com.cloud.log.annotation.BraveSysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.cloud.core.base.controller.BaseController;

/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 *
 * @author yongchen
 * @since 2021-06-17
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/sysRole")
@Api(value = "SysRoleController", tags = "角色信息")
public class SysRoleController extends BaseController {

    private final SysRoleService roleService;

    /**
     * @Author: yongchen
     * @Description: 角色分页查询
     * @Date: 17:16 2021/6/17
     * @Param: [pp]
     * @return: com.cloud.core.result.Result<com.baomidou.mybatisplus.core.metadata.IPage < com.cloud.brave.entity.SysRole>>
     **/
    @PostMapping("/page")
    @BraveSysLog(value = "角色分页查询")
    @ApiOperation(value = "角色分页查询", notes = "角色分页查询")
    public Result<IPage<SysRole>> page(@RequestBody @Validated PageParam<SysRole> pp) {
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        Page<SysRole> page = roleService.page(pp.getPage(), wrapper);
        return success(page);
    }

    /**
     * @Author: yongchen
     * @Description: 根据角色id获取角色详情
     * @Date: 17:19 2021/6/17
     * @Param: [id]
     * @return: com.cloud.core.result.Result<com.cloud.brave.entity.SysRole>
     **/
    @GetMapping("/get")
    @BraveSysLog(value = "根据角色id获取角色详情")
    @ApiOperation(value = "根据角色id获取角色详情", notes = "根据角色id获取角色详情")
    public Result<SysRole> get(@RequestParam("id") Long id) {
        return success(roleService.getById(id));
    }

    /**
     * @Author: yongchen
     * @Description: 添加角色信息
     * @Date: 17:23 2021/6/17
     * @Param: [sysRole]
     * @return: com.cloud.core.result.Result<java.lang.Boolean>
     **/
    @PostMapping("/save")
    @BraveSysLog(value = "添加角色信息")
    @ApiOperation(value = "添加角色信息", notes = "添加角色信息")
    public Result<Boolean> save(@RequestBody SysRole sysRole) {
        if (roleService.save(sysRole)) {
            return success(true);
        }
        return failed("添加角色信息异常");
    }

    /**
     * @Author: yongchen
     * @Description: 更新角色信息
     * @Date: 17:27 2021/6/17
     * @Param: [sysRole]
     * @return: com.cloud.core.result.Result<java.lang.Boolean>
     **/
    @PostMapping("/update")
    @BraveSysLog(value = "更新角色信息")
    @ApiOperation(value = "更新角色信息", notes = "更新角色信息")
    public Result<Boolean> update(@RequestBody SysRole sysRole) {
        if (sysRole.getId() == null) {
            return failed("主键不能为空");
        }
        if (roleService.updateById(sysRole)) {
            return success(true);
        }
        return failed("更新角色信息异常");
    }

    /**
     * @Author: yongchen
     * @Description: 删除角色
     * @Date: 17:29 2021/6/17
     * @Param: [id]
     * @return: com.cloud.core.result.Result<java.lang.Boolean>
     **/
    @GetMapping("/delete")
    @BraveSysLog(value = "删除角色")
    @ApiOperation(value = "删除角色", notes = "删除角色")
    public Result<Boolean> delete(@RequestParam("id") Long id) {
        if (roleService.removeById(id)) {
            return success(true);
        }
        return failed("删除角色异常");
    }
}
