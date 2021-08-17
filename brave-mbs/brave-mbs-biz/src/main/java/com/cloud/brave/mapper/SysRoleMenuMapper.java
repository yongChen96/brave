package com.cloud.brave.mapper;

import com.cloud.brave.dto.RolePremsDTO;
import com.cloud.brave.entity.SysMenu;
import com.cloud.brave.entity.SysRoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 角色资源关联信息表 Mapper 接口
 * </p>
 *
 * @author yongchen
 * @since 2021-06-18
 */
@Repository
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    /**
     * @Author yongchen
     * @Description 获取角色权限信息
     * @Date 16:01 2021/7/27
     * @param
     * @return java.util.List<com.cloud.brave.dto.RolePremsDTO>
     **/
    List<RolePremsDTO> getRolePerms();

    /**
     * @Author yongchen
     * @Description 获取角色资源信息
     * @Date 16:00 2021/7/27
     * @param roleId
     * @return java.util.List<com.cloud.brave.dto.RolePremsDTO>
     **/
    List<SysMenu> getMenuByRole(Long roleId);
}
