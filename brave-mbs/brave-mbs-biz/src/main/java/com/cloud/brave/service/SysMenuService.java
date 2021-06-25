package com.cloud.brave.service;

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
     * @Description 获取资源权限信息
     * @Date 15:14 2021/6/25
     * @param
     * @return java.util.List<com.cloud.brave.entity.SysMenu>
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
