package com.cloud.brave.service;

import com.cloud.brave.dto.MenuDTO;
import com.cloud.brave.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 资源信息表 服务类
 * </p>
 *
 * @author yongchen
 * @since 2021-06-18
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * @Author yongchen
     * @Description 获取当前用户菜单路由信息
     * @Date 10:46 2021/7/9
     * @param userId
     * @return java.util.List<com.cloud.brave.dto.MenuDTO>
     **/
    List<MenuDTO> findMenusByUserId(Long userId);

    /**
     * @param
     * @return java.util.List<com.cloud.brave.entity.SysMenu>
     * @Author yongchen
     * @Description 获取资源权限信息
     * @Date 15:14 2021/6/25
     **/
    List<SysMenu> findMenu();

    /**
     * @Author: yongchen
     * @Description: 根据角色获取访问权限
     * @Date: 17:31 2021/6/18
     * @Param: [roleId]
     * @return: java.util.List<com.cloud.brave.entity.SysMenu>
     **/
    List<SysMenu> findPermissionsByRoleId(Long roleId);

    /**
     * @Author yongchen
     * @Description 获取角色树
     * @Date 17:14 2021/7/22
     * @param menuName 资源名称
     * @param isExternalLink 是否外链
     * @param menuType 资源类型
     * @param isDisplay 显示状态
     * @param status 资源状态
     * @return java.util.List<com.cloud.brave.dto.MenuDTO>
     **/
    List<MenuDTO> getMenuTree(String menuName, String isExternalLink, String menuType, String isDisplay, String status);
}
