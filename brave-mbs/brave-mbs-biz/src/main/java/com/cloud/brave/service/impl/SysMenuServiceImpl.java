package com.cloud.brave.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cloud.brave.entity.SysMenu;
import com.cloud.brave.mapper.SysMenuMapper;
import com.cloud.brave.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.core.constant.CommonConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 资源信息表 服务实现类
 * </p>
 *
 * @author yongchen
 * @since 2021-06-18
 */
@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    private final SysMenuMapper sysMenuMapper;
    private final RedisTemplate redisTemplate;

    /**
     * @Author yongchen
     * @Description 获取资源权限信息
     * @Date 15:14 2021/6/25
     * @param
     * @return java.util.List<com.cloud.brave.entity.SysMenu>
     **/
    @Override
    public List<SysMenu> findMenu() {
        List<SysMenu> sysMenus = sysMenuMapper.findPermissionsRole();

        return null;
    }

    /**
     * @Author: yongchen
     * @Description: 根据角色获取访问权限
     * @Date: 17:32 2021/6/18
     * @Param: [roleId]
     * @return: java.util.List<com.cloud.brave.entity.SysMenu>
     **/
    @Override
    public List<SysMenu> findPermissionsByRoleId(Long roleId) {
        return sysMenuMapper.findPermissionsByRoleId(roleId);
    }
}
