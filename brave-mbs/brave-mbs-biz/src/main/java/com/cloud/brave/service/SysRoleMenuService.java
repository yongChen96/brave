package com.cloud.brave.service;

import com.cloud.brave.dto.RolePremsDTO;
import com.cloud.brave.entity.SysRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色资源关联信息表 服务类
 * </p>
 *
 * @author yongchen
 * @since 2021-06-18
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

    /**
     * @Author yongchen
     * @Description 根据角色id删除角色资源信息
     * @Date 11:34 2021/7/23
     * @param roleId
     * @return java.lang.Boolean
     **/
    Boolean removeRoleMenu(Long roleId);

    /**
     * @Author yongchen
     * @Description 获取角色权限信息
     * @Date 16:00 2021/7/27
     * @param
     * @return java.util.List<com.cloud.brave.dto.RolePremsDTO>
     **/
    List<RolePremsDTO> getRolePerms();
}
