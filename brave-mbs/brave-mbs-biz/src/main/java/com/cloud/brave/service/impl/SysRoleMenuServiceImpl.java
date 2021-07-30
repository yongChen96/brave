package com.cloud.brave.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cloud.brave.dto.RolePremsDTO;
import com.cloud.brave.entity.SysRoleMenu;
import com.cloud.brave.mapper.SysRoleMenuMapper;
import com.cloud.brave.service.SysRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.core.exception.BraveException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 角色资源关联信息表 服务实现类
 * </p>
 *
 * @author yongchen
 * @since 2021-06-18
 */
@Service
@RequiredArgsConstructor
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

    private final SysRoleMenuMapper sysRoleMenuMapper;

    /**
     * @Author yongchen
     * @Description 根据角色id批量删除角色资源信息
     * @Date 11:35 2021/7/23
     * @param roleId
     * @return java.lang.Boolean
     **/
    @Override
    public Boolean removeRoleMenu(Long roleId) {
        if (null != roleId){
            LambdaQueryWrapper<SysRoleMenu> removeByRoleIds = new LambdaQueryWrapper<>();
            removeByRoleIds.eq(SysRoleMenu::getRoleId, roleId);
            if (sysRoleMenuMapper.delete(removeByRoleIds) >= 0) {
                return true;
            }
            return false;
        }
        throw new BraveException("角色id不能为空");
    }

    /**
     * @Author yongchen
     * @Description 获取角色权限信息
     * @Date 16:01 2021/7/27
     * @param
     * @return java.util.List<com.cloud.brave.dto.RolePremsDTO>
     **/
    @Override
    public List<RolePremsDTO> getRolePerms() {
        return sysRoleMenuMapper.getRolePerms();
    }
}
