package com.cloud.brave.service;

import com.cloud.brave.dto.MenuDTO;
import com.cloud.brave.dto.MenuRouterDTO;
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
     * @Author yongchen
     * @Description 构建菜单结构
     * @Date 15:22 2021/7/9
     * @param menus
     * @return java.util.List<com.cloud.brave.dto.MenuRouterDTO>
     **/
    List<MenuRouterDTO> buildMenus(List<MenuDTO> menus);

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

}
