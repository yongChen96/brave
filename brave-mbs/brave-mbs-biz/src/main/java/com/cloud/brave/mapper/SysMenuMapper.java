package com.cloud.brave.mapper;

import com.cloud.brave.dto.MenuDTO;
import com.cloud.brave.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 资源信息表 Mapper 接口
 * </p>
 *
 * @author yongchen
 * @since 2021-06-18
 */
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * @Author yongchen
     * @Description 获取权限角色信息
     * @Date 16:42 2021/6/25
     * @param
     * @return java.util.List<com.cloud.brave.entity.SysMenu>
     **/
    List<SysMenu> findPermissionsRole();

    /**
     * @Author: yongchen
     * @Description: 根据角色获取访问权限
     * @Date: 17:33 2021/6/18
     * @Param: [roleId]
     * @return: java.util.List<com.cloud.brave.entity.SysMenu>
     **/
    List<SysMenu> findPermissionsByRoleId(@Param("roleId") Long roleId);

    /**
     * @Author yongchen
     * @Description 获取当前用户菜单路由信息
     * @Date 10:50 2021/7/9
     * @param userId
     * @return java.util.List<com.cloud.brave.dto.MenuDTO>
     **/
    List<MenuDTO> findMenusByUserId(@Param("userId") Long userId);
}
