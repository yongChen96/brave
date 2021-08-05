package com.cloud.brave.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cloud.brave.dto.UserDTO;
import com.cloud.brave.dto.UserDetailsDTO;
import com.cloud.brave.dto.UserInfoDTO;
import com.cloud.brave.entity.*;
import com.cloud.brave.mapper.SysUserMapper;
import com.cloud.brave.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.brave.core.SnowflakeId.IdGenerate;
import com.cloud.brave.core.constant.CommonConstants;
import com.cloud.brave.core.exception.BraveException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
    private final SysDeptService sysDeptService;
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
                .eq(SysUser::getDelState, CommonConstants.NOT_DELETED);
        SysUser sysUser = this.getOne(sysUserLambdaQueryWrapper);
        List<Long> roleIds = sysRoleService.findRolesByUserId(sysUser.getId()).stream().map(SysRole::getId)
                .collect(Collectors.toList());
        Set<String> permissions = new HashSet<>();
        roleIds.forEach(roleId -> {
            List<String> list = sysMenuService.findPermissionsByRoleId(roleId).stream()
                    .filter(menu -> StrUtil.isNotEmpty(menu.getBtnPerm()))
                    .map(SysMenu::getBtnPerm)
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
        sysUser.setPassWord(String.format("%s%s",CommonConstants.PASSWORD_PREFIX,passwordEncoder.encode(CommonConstants.USER_INITIAL_PWD)));
        sysUserMapper.insert(sysUser);
        // 添加角色关联信息
//        List<SysUserRole> sysUserRoles = Convert.toList(Long.class, userDTO.getRoles()).stream().map(roleId -> {
        List<SysUserRole> sysUserRoles = userDTO.getRoles().stream().map(roleId -> {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(sysUser.getId());
            sysUserRole.setRoleId(roleId);
            return sysUserRole;
        }).collect(Collectors.toList());
        return sysUserRoleService.saveBatch(sysUserRoles);
    }

    /**
     * @Author yongchen
     * @Description 更新用户信息
     * @Date 9:11 2021/7/21
     * @param userDTO
     * @return java.lang.Boolean
     **/
    @Override
    public Boolean updateUser(UserDTO userDTO) {
        SysUser user = new SysUser();
        BeanUtils.copyProperties(userDTO, user);
        if (this.updateById(user)) {
            // 角色信息更新
            LambdaQueryWrapper<SysUserRole> userRoleWpper = new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, userDTO.getId());
            List<SysUserRole> list = sysUserRoleService.list(userRoleWpper);
            if (!CollectionUtils.isEmpty(list)){
                List<Long> ids = list.stream().map(item -> item.getId()).collect(Collectors.toList());
                sysUserRoleService.removeByIds(ids);
            }

            List<Long> roles = userDTO.getRoles();
            if (!CollectionUtils.isEmpty(roles)){
                for (Long role : roles) {
                    SysUserRole sysUserRole = new SysUserRole();
                    sysUserRole.setUserId(userDTO.getId());
                    sysUserRole.setRoleId(role);
                    if (!sysUserRoleService.save(sysUserRole)) {
                        throw new BraveException("更新角色信息失败");
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * @Author: yongchen
     * @Description: 修改用户锁定状态
     * @Date: 15:55 2021/6/8
     * @Param: [id, locakStatus]
     * @return: java.lang.Boolean
     **/
    @Override
    public Boolean locking(Long id, String locakStatus) {
        LambdaUpdateWrapper<SysUser> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(SysUser::getId, id)
                .set(SysUser::getIsLock, locakStatus);
        return this.update(updateWrapper);
    }

    /**
     * @param id
     * @return com.cloud.brave.dto.UserDetailsDTO
     * @Author yongchen
     * @Description 获取用户详细信息
     * @Date 15:07 2021/7/15
     **/
    @Override
    public UserDetailsDTO getUserDetailsById(Long id) {
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        SysUser sysUser = getById(id);
        if (null == sysUser) {
            throw new BraveException("用户信息不存在");
        }
        BeanUtils.copyProperties(sysUser, userDetailsDTO);
        //用户本门信息
        SysDept dept = sysDeptService.getById(userDetailsDTO.getDeptId());
        userDetailsDTO.setSysDept(dept);
        //用户角色信息
        List<SysRole> roles = sysRoleService.findRolesByUserId(id);
        List<Long> collect = roles.stream().map(SysRole::getId).collect(Collectors.toList());
        userDetailsDTO.setRoles(collect);
        return userDetailsDTO;
    }

    /**
     * @Author yongchen
     * @Description 重置用户密码
     * @Date 10:03 2021/7/19
     * @param id
     * @return boolean
     **/
    @Override
    public boolean resetPassword(Long id) {
        // 逻辑删除用户信息
        LambdaUpdateWrapper<SysUser> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(SysUser::getId, id)
                        .set(SysUser::getPassWord, passwordEncoder.encode(CommonConstants.USER_INITIAL_PWD));
        if (this.update(updateWrapper)) {
            //物理删除用户角色中间信息
            LambdaUpdateWrapper<SysUserRole> userRoleWapper = new LambdaUpdateWrapper<>();
            userRoleWapper.eq(SysUserRole::getUserId, id);
            return sysUserRoleService.remove(userRoleWapper);
        }
        return false;
    }
}
