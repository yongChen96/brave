package com.cloud.brave.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cloud.brave.core.constant.AuthConstants;
import com.cloud.brave.dto.RolePremsDTO;
import com.cloud.brave.entity.SysMenu;
import com.cloud.brave.entity.SysRoleMenu;
import com.cloud.brave.mapper.SysRoleMenuMapper;
import com.cloud.brave.service.SysRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.brave.core.exception.BraveException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    private final RedisTemplate redisTemplate;
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
     * @return void
     **/
    @Override
    public void getRolePerms() {
        redisTemplate.delete(AuthConstants.URL_PERM_ROLES_KEY);
        List<RolePremsDTO> rolePerms = sysRoleMenuMapper.getRolePerms();
        if (!CollectionUtils.isEmpty(rolePerms)) {
            //初始化角色-权限规则
            List<RolePremsDTO> urlPerms = rolePerms
                    .stream()
                    .filter(item -> StringUtils.isNotBlank(item.getUrlPerm()))
                    .collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(urlPerms)) {
                Map<String, List<Long>> urlPermRoles = new HashMap<>();
                urlPerms.stream().forEach(item -> {
                    String perms = item.getUrlPerm();
                    List<Long> roleCodes = item.getRoles();
                    urlPermRoles.put(perms, roleCodes);
                });
                redisTemplate.opsForHash().putAll(AuthConstants.URL_PERM_ROLES_KEY, urlPermRoles);
            }
        }
    }

    /**
     * @Author yongchen
     * @Description 获取角色资源信息
     * @Date 16:00 2021/7/27
     * @param roleId
     * @return java.util.List<com.cloud.brave.dto.RolePremsDTO>
     **/
    @Override
    public List<SysMenu> getMenuByRole(Long roleId) {
        return sysRoleMenuMapper.getMenuByRole(roleId);
    }
}
