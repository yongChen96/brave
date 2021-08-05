package com.cloud.brave.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.brave.dto.MenuDTO;
import com.cloud.brave.entity.SysMenu;
import com.cloud.brave.service.SysMenuService;
import com.cloud.brave.core.constant.CommonConstants;
import com.cloud.brave.core.exception.BraveException;
import com.cloud.brave.core.mybatisplus.page.PageParam;
import com.cloud.brave.core.result.Result;
import com.cloud.brave.log.annotation.BraveSysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.cloud.brave.core.base.controller.BaseController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
     * @param pp
     * @return com.cloud.core.result.Result<com.baomidou.mybatisplus.core.metadata.IPage < com.cloud.brave.entity.SysMenu>>
     * @Author yongchen
     * @Description 分页查询菜单信息
     * @Date 14:16 2021/7/14
     **/
    @PostMapping("/page")
    @BraveSysLog(value = "分页查询菜单信息")
    @ApiOperation(value = "分页查询菜单信息", notes = "分页查询菜单信息")
    public Result<IPage<SysMenu>> page(@RequestBody @Validated PageParam<SysMenu> pp) {
        LambdaQueryWrapper<SysMenu> pageWapper = new LambdaQueryWrapper<>();
        return success(sysMenuService.page(pp.getPage(), pageWapper));
    }

    /**
     * @Author yongchen
     * @Description 获取菜单列表
     * @Date 17:16 2021/7/22
     * @param menuName 资源名称
     * @param isExternalLink 是否外链
     * @param menuType 资源类型
     * @param isDisplay 显示状态
     * @param status 资源状态
     * @return com.cloud.core.result.Result<java.util.List<com.cloud.brave.dto.MenuDTO>>
     **/
    @GetMapping("/getMenuTree")
    @BraveSysLog("获取菜单列表")
    @ApiOperation(value = "获取菜单列表", notes = "获取菜单列表")
    public Result<List<MenuDTO>> getMenuTree(@RequestParam(value = "menuName", required = false) String menuName,
                                             @RequestParam(value = "isExternalLink", required = false) String isExternalLink,
                                             @RequestParam(value = "menuType", required = false) String menuType,
                                             @RequestParam(value = "isDisplay", required = false) String isDisplay,
                                             @RequestParam(value = "status", required = false) String status) {
        return success(sysMenuService.getMenuTree(menuName, isExternalLink, menuType, isDisplay, status));
    }

    /**
     * @param
     * @return com.cloud.core.result.Result<java.util.List < MenuRouterDTO>>
     * @Author yongchen
     * @Description 获取当前用户菜单路由信息
     * @Date 10:43 2021/7/9
     **/
    @GetMapping("/getRouters")
    @ApiOperation(value = "获取当前用户菜单路由信息", notes = "获取当前用户菜单路由信息")
    public Result<List<MenuDTO>> getRouters() {
        // TODO 获取当前登录用户id
        Long userId = 1L;
        List<MenuDTO> menus = sysMenuService.findMenusByUserId(userId);
        List<MenuDTO> collect = menus.stream().sorted(Comparator.comparing(MenuDTO::getSort)).collect(Collectors.toList());
        return success(collect);
    }

    /**
     * @param id
     * @return com.cloud.core.result.Result<com.cloud.brave.entity.SysMenu>
     * @Author yongchen
     * @Description 获取资源详细信息
     * @Date 16:42 2021/7/22
     **/
    @GetMapping("/get")
    @BraveSysLog(value = "获取资源详细信息")
    @ApiOperation(value = "获取资源详细信息", notes = "获取资源详细信息")
    public Result<SysMenu> get(@RequestParam Long id) {
        return success(sysMenuService.getById(id));
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
     * @Description 修改资源状态
     * @Date 16:20 2021/6/23
     **/
    @GetMapping("/updateStatus")
    @BraveSysLog(value = "修改资源状态")
    @ApiOperation(value = "修改资源状态", notes = "修改资源状态")
    public Result<Boolean> updateStatus(@RequestParam Long id, @RequestParam String status) {
        if (StringUtils.equals(CommonConstants.DISABLE, status)) {
            //判断：下级资源使用中则无法禁用资源
            LambdaQueryWrapper<SysMenu> menuWapper = new LambdaQueryWrapper<>();
            menuWapper.eq(SysMenu::getParentId, id).eq(SysMenu::getStatus, CommonConstants.ENABLE);
            if (sysMenuService.count(menuWapper) > 0) {
                throw new BraveException("下级资源使用中，请先禁用下级资源");
            }
        } else {
            //判断上级资源是否禁用，上级禁用则不能启用
            SysMenu sysMenu = sysMenuService.getById(id);
            LambdaQueryWrapper<SysMenu> superMenuWapepr = new LambdaQueryWrapper<>();
            superMenuWapepr.eq(SysMenu::getId, sysMenu.getParentId()).eq(SysMenu::getStatus, CommonConstants.DISABLE);
            if (sysMenuService.count(superMenuWapepr) > 0) {
                throw new BraveException("上级资源被禁用，请先启用上级资源");
            }
        }

        LambdaUpdateWrapper<SysMenu> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(SysMenu::getId, id)
                .set(SysMenu::getStatus, status);
        if (sysMenuService.update(wrapper)) {
            return success(true);
        }
        return failed("修改资源状态失败");
    }

    /**
     * @param id 菜单主键id
     * @return com.cloud.core.result.Result<java.lang.Boolean>
     * @Author yongchen
     * @Description 修改资源显示状态
     * @Date 16:24 2021/6/23
     **/
    @GetMapping("/updateDisplay")
    @BraveSysLog(value = "修改资源显示状态")
    @ApiOperation(value = "修改资源显示状态", notes = "修改资源显示状态")
    public Result<Boolean> updateDisplay(@RequestParam Long id, @RequestParam String display) {
        LambdaUpdateWrapper<SysMenu> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(SysMenu::getId, id)
                .set(SysMenu::getIsDisplay, display);
        if (sysMenuService.update(wrapper)) {
            return success(true);
        }
        return failed("修改资源显示状态失败");
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
        //判断是否存在下级资源，存在则不能删除
        LambdaQueryWrapper<SysMenu> menuWapper = new LambdaQueryWrapper<>();
        menuWapper.eq(SysMenu::getParentId, id).eq(SysMenu::getDelState, CommonConstants.NOT_DELETED);
        if (sysMenuService.count(menuWapper) > 0) {
            throw new BraveException("该资源下存在使用资源，无法删除");
        }

        LambdaUpdateWrapper<SysMenu> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(SysMenu::getId, id)
                .set(SysMenu::getDelState, CommonConstants.DELETED);
        if (sysMenuService.update(wrapper)) {
            return success(true);
        }
        return failed("删除菜单信息");
    }
}
