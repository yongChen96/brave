package com.cloud.brave.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.brave.dto.MenuDTO;
import com.cloud.brave.dto.MenuRouterDTO;
import com.cloud.brave.dto.RoleMenuTreeDTO;
import com.cloud.brave.entity.SysMenu;
import com.cloud.brave.service.SysMenuService;
import com.cloud.core.constant.CommonConstants;
import com.cloud.core.mybatisplus.page.PageParam;
import com.cloud.core.result.Result;
import com.cloud.log.annotation.BraveSysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.cloud.core.base.controller.BaseController;

import java.util.List;

/**
 * <p>
 * 资源信息表 前端控制器
 * </p>
 *
 * @author yongchen
 * @since 2021-06-18
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/sysMenu")
@Api(value = "SysMenuController", tags = "资源信息表")
public class SysMenuController extends BaseController {

    private final SysMenuService sysMenuService;

    /**
     * @Author yongchen
     * @Description 分页查询菜单信息
     * @Date 14:16 2021/7/14
     * @param pp
     * @return com.cloud.core.result.Result<com.baomidou.mybatisplus.core.metadata.IPage<com.cloud.brave.entity.SysMenu>>
     **/
    @PostMapping("/page")
    @BraveSysLog(value = "分页查询菜单信息")
    @ApiOperation(value = "分页查询菜单信息", notes = "分页查询菜单信息")
    public Result<IPage<SysMenu>> page(@RequestBody @Validated PageParam<SysMenu> pp){
        LambdaQueryWrapper<SysMenu> pageWapper = new LambdaQueryWrapper<>();
        return success(sysMenuService.page(pp.getPage(),pageWapper));
    }

    /**
     * @Author yongchen
     * @Description 获取菜单列表
     * @Date 14:30 2021/7/14
     * @param sysMenu
     * @return com.cloud.core.result.Result<java.util.List<com.cloud.brave.dto.MenuTreeDTO>>
     **/
    @PostMapping("/list")
    @BraveSysLog("获取菜单列表")
    @ApiOperation(value = "获取菜单列表", notes = "获取菜单列表")
    public Result<List<SysMenu>> list(@RequestBody SysMenu sysMenu){
        LambdaQueryWrapper<SysMenu> menuListWapper = new LambdaQueryWrapper<>();
        menuListWapper.eq(SysMenu::getStatus, CommonConstants.ENABLE)
                        .eq(SysMenu::getDelState, CommonConstants.NOT_DELETED);
        return success(sysMenuService.list(menuListWapper));
    }

    /**
     * @Author yongchen
     * @Description 获取当前用户菜单路由信息
     * @Date 10:43 2021/7/9
     * @param
     * @return com.cloud.core.result.Result<java.util.List<MenuRouterDTO>>
     **/
    @GetMapping("/getRouters")
    @ApiOperation(value = "获取当前用户菜单路由信息", notes = "获取当前用户菜单路由信息")
    public Result<List<MenuRouterDTO>> getRouters(){
        // TODO 获取当前登录用户id
        Long userId = 1L;
        List<MenuDTO> menus = sysMenuService.findMenusByUserId(userId);
        return success(sysMenuService.buildMenus(menus));
    }

    /**
     * @param
     * @return com.cloud.core.result.Result<java.util.List < com.cloud.brave.entity.SysMenu>>
     * @Author yongchen
     * @Description 获取菜单列表
     * @Date 16:09 2021/6/23
     **/
    @GetMapping("/getMenus")
    @BraveSysLog(value = "获取菜单列表")
    @ApiOperation(value = "获取菜单列表", notes = "获取菜单列表")
    public Result<List<SysMenu>> getMenus() {
        LambdaQueryWrapper<SysMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysMenu::getDelState, CommonConstants.NOT_DELETED)
                .eq(SysMenu::getStatus, CommonConstants.ENABLE);
        return success(sysMenuService.list(queryWrapper));
    }

    /**
     * @param sysMenu 菜单信息实体类
     * @return com.cloud.core.result.Result<java.lang.Boolean>
     * @Author yongchen
     * @Description 新增菜单
     * @Date 16:12 2021/6/23
     **/
    @PostMapping("/save")
    @BraveSysLog(value = "新增菜单")
    @ApiOperation(value = "新增菜单", notes = "新增菜单")
    public Result<Boolean> save(@RequestBody @Validated SysMenu sysMenu) {
        if (sysMenuService.save(sysMenu)) {
            return success(true);
        }
        return failed("新增菜单失败");
    }

    /**
     * @param sysMenu 更新菜单实体类
     * @return com.cloud.core.result.Result<java.lang.Boolean>
     * @Author yongchen
     * @Description 更新菜单
     * @Date 16:14 2021/6/23
     **/
    @PostMapping("/update")
    @BraveSysLog(value = "更新菜单")
    @ApiOperation(value = "更新菜单", notes = "更新菜单")
    public Result<Boolean> update(@RequestBody @Validated SysMenu sysMenu) {
        if (sysMenuService.updateById(sysMenu)) {
            return success(true);
        }
        return failed("更新菜单失败");
    }

    /**
     * @param id 菜单主键id
     * @return com.cloud.core.result.Result<java.lang.Boolean>
     * @Author yongchen
     * @Description 禁用菜单
     * @Date 16:20 2021/6/23
     **/
    @GetMapping("/disableMenu")
    @BraveSysLog(value = "禁用菜单")
    @ApiOperation(value = "禁用菜单", notes = "禁用菜单")
    public Result<Boolean> disableMenu(@RequestParam Long id) {
        LambdaUpdateWrapper<SysMenu> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(SysMenu::getId, id)
                .set(SysMenu::getStatus, CommonConstants.DISABLE);
        if (sysMenuService.update(wrapper)) {
            return success(true);
        }
        return failed("禁用菜单失败");
    }

    /**
     * @param id 菜单主键id
     * @return com.cloud.core.result.Result<java.lang.Boolean>
     * @Author yongchen
     * @Description 启用菜单
     * @Date 16:24 2021/6/23
     **/
    @GetMapping("/enableMenu")
    @BraveSysLog(value = "启用菜单")
    @ApiOperation(value = "启用菜单", notes = "启用菜单")
    public Result<Boolean> enableMenu(@RequestParam Long id) {
        LambdaUpdateWrapper<SysMenu> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(SysMenu::getId, id)
                .set(SysMenu::getStatus, CommonConstants.ENABLE);
        if (sysMenuService.update(wrapper)) {
            return success(true);
        }
        return failed("启用菜单失败");
    }

    /**
     * @param id 菜单主键id
     * @return com.cloud.core.result.Result<java.lang.Boolean>
     * @Author yongchen
     * @Description 删除菜单
     * @Date 16:26 2021/6/23
     **/
    @GetMapping("/delete")
    @BraveSysLog(value = "删除菜单")
    @ApiOperation(value = "删除菜单", notes = "删除菜单")
    public Result<Boolean> delete(@RequestParam Long id) {
        LambdaUpdateWrapper<SysMenu> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(SysMenu::getId, id)
                .set(SysMenu::getDelState, CommonConstants.DELETED);
        if (sysMenuService.update(wrapper)) {
            return success(true);
        }
        return failed("删除菜单信息");
    }

    @GetMapping("/roleMenuTreeselect")
    public Result<RoleMenuTreeDTO>  roleMenuTreeselect(@RequestParam Long id){
        RoleMenuTreeDTO dto = sysMenuService.roleMenuTreeselect(id);
        return null;
    }

}
