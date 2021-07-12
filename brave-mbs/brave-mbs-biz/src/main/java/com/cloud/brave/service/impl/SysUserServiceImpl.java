package com.cloud.brave.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cloud.brave.dto.UserDTO;
import com.cloud.brave.dto.UserInfoDTO;
import com.cloud.brave.entity.SysMenu;
import com.cloud.brave.entity.SysRole;
import com.cloud.brave.entity.SysUser;
import com.cloud.brave.entity.SysUserRole;
import com.cloud.brave.mapper.SysUserMapper;
import com.cloud.brave.service.SysMenuService;
import com.cloud.brave.service.SysRoleService;
import com.cloud.brave.service.SysUserRoleService;
import com.cloud.brave.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.core.SnowflakeId.IdGenerate;
import com.cloud.core.constant.CommonConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author yongchen
 * @since 2021-06-03
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final SysUserMapper sysUserMapper;
    private final SysRoleService sysRoleService;
    private final SysUserRoleService sysUserRoleService;
    private final SysMenuService sysMenuService;
    private final IdGenerate<Long> idGenerate;


    /**
     * @Author: yongchen
     * @Description: 获取用户信息
     * @Date: 14:53 2021/6/4
     * @Param: [username]
     * @return: com.cloud.brave.dto.UserInfoDTO
     **/
    @Override
    public UserInfoDTO getUserInfo(String username) {
        LambdaQueryWrapper<SysUser> sysUserLambdaQueryWrapper = new LambdaQueryWrapper<>();
        sysUserLambdaQueryWrapper.eq(SysUser::getPhone, username)
                                    .eq(SysUser::getIsLock, CommonConstants.IS_LOCK_NO)
                                    .eq(SysUser::getDelState, CommonConstants.NOT_DELETED);
        SysUser sysUser = this.getOne(sysUserLambdaQueryWrapper);
        List<Long> roleIds = sysRoleService.findRolesByUserId(sysUser.getId()).stream().map(SysRole::getId)
                .collect(Collectors.toList());
        Set<String> permissions = new HashSet<>();
        roleIds.forEach(roleId -> {
            List<String> list = sysMenuService.findPermissionsByRoleId(roleId).stream()
                    .filter(menu -> StrUtil.isNotEmpty(menu.getPerms())).map(SysMenu::getPerms)
                    .collect(Collectors.toList());
            permissions.addAll(list);
        });
        return new UserInfoDTO(sysUser, ArrayUtil.toArray(permissions, String.class), ArrayUtil.toArray(roleIds, Long.class));
    }

    /**
     * @Author: yongchen
     * @Description: 添加新用户信息
     * @Date: 11:02 2021/6/7
     * @Param: [userDTO]
     * @return: java.lang.Boolean
     **/
    @Override
    public Boolean saveNewUser(UserDTO userDTO) {
        // 添加用户信息
        SysUser sysUser = new SysUser();
        Long id = idGenerate.idGenerate();
        BeanUtils.copyProperties(userDTO, sysUser);
        sysUser.setId(id);
        sysUser.setDelState(CommonConstants.NOT_DELETED);
        sysUser.setIsLock(CommonConstants.IS_LOCK_NO);
        sysUser.setPassWord(passwordEncoder.encode(sysUser.getPassWord()));
        sysUserMapper.insert(sysUser);
        // 添加角色关联信息
        List<SysUserRole> sysUserRoles = userDTO.getRoles().stream().map(roleId -> {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(sysUser.getId());
            sysUserRole.setRoleId(roleId);
            return sysUserRole;
        }).collect(Collectors.toList());
        return sysUserRoleService.saveBatch(sysUserRoles);
    }

    /**
     * @Author: yongchen
     * @Description: 锁定用户
     * @Date: 15:55 2021/6/8
     * @Param: [id]
     * @return: java.lang.Boolean
     **/
    @Override
    public Boolean locking(Long id) {
        LambdaUpdateWrapper<SysUser> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(SysUser::getId, id)
                        .eq(SysUser::getIsLock, CommonConstants.IS_LOCK_YES);
        return this.update(updateWrapper);
    }

    /**
     * @Author: yongchen
     * @Description: 用户解锁
     * @Date: 15:59 2021/6/8
     * @Param: [id]
     * @return: java.lang.Boolean
     **/
    @Override
    public Boolean unlock(Long id) {
        LambdaUpdateWrapper<SysUser> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(SysUser::getId, id)
                .eq(SysUser::getIsLock, CommonConstants.IS_LOCK_NO);
        return this.update(updateWrapper);
    }
}
