package com.cloud.brave.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cloud.brave.core.constant.AuthConstants;
import com.cloud.brave.core.utils.JwtUtils;
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
import com.cloud.brave.email.entity.Mail;
import com.cloud.brave.email.service.SendMailService;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
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
    private final RedisTemplate redisTemplate;
    private final SendMailService sendMailService;

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
        if (null == sysUser) {
            throw new BraveException("用户不存在");
        }
        //获取用户所属部门信息
        SysDept dept = sysDeptService.getById(sysUser.getDeptId());
        //获取用户角色信息
        List<Long> roleIds = sysRoleService.findRolesByUserId(sysUser.getId()).stream().map(SysRole::getId)
                .collect(Collectors.toList());
        //获取用户权限信息
        Set<String> permissions = new HashSet<>();
        roleIds.forEach(roleId -> {
            List<String> list = sysMenuService.findPermissionsByRoleId(roleId).stream()
                    .filter(menu -> StrUtil.isNotEmpty(menu.getBtnPerm()))
                    .map(SysMenu::getBtnPerm)
                    .collect(Collectors.toList());
            permissions.addAll(list);
        });
        return new UserInfoDTO(sysUser, dept, ArrayUtil.toArray(permissions, String.class), ArrayUtil.toArray(roleIds, Long.class));
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
        // 一个手机/邮箱只能注册一个用户
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getPhone, userDTO.getPhone())
                .or().eq(SysUser::getEmail, userDTO.getEmail())
                .eq(SysUser::getDelState, CommonConstants.NOT_DELETED);
        if (count(queryWrapper) > 0) {
            throw new BraveException("手机号/邮箱已使用");
        }

        // 添加用户信息
        SysUser sysUser = new SysUser();
        Long id = idGenerate.idGenerate();
        BeanUtils.copyProperties(userDTO, sysUser);
        sysUser.setId(id);
        sysUser.setDelState(CommonConstants.NOT_DELETED);
        sysUser.setIsLock(CommonConstants.IS_LOCK_NO);
        sysUser.setPassWord(passwordEncoder.encode(AuthConstants.USER_INITIAL_PWD));
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
     * @param userDTO
     * @return java.lang.Boolean
     * @Author yongchen
     * @Description 更新用户信息
     * @Date 9:11 2021/7/21
     **/
    @Override
    public Boolean updateUser(UserDTO userDTO) {
        SysUser user = new SysUser();
        BeanUtils.copyProperties(userDTO, user);
        if (this.updateById(user)) {
            // 角色信息更新
            LambdaQueryWrapper<SysUserRole> userRoleWpper = new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, userDTO.getId());
            List<SysUserRole> list = sysUserRoleService.list(userRoleWpper);
            if (!CollectionUtils.isEmpty(list)) {
                List<Long> ids = list.stream().map(item -> item.getId()).collect(Collectors.toList());
                sysUserRoleService.removeByIds(ids);
            }

            List<Long> roles = userDTO.getRoles();
            if (!CollectionUtils.isEmpty(roles)) {
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
     * @param id
     * @return boolean
     * @Author yongchen
     * @Description 重置用户密码
     * @Date 10:03 2021/7/19
     **/
    @Override
    public boolean resetPassword(Long id) {
        // 逻辑删除用户信息
        LambdaUpdateWrapper<SysUser> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(SysUser::getId, id)
                .set(SysUser::getPassWord, passwordEncoder.encode(AuthConstants.USER_INITIAL_PWD));
        if (this.update(updateWrapper)) {
            //物理删除用户角色中间信息
            LambdaUpdateWrapper<SysUserRole> userRoleWapper = new LambdaUpdateWrapper<>();
            userRoleWapper.eq(SysUserRole::getUserId, id);
            return sysUserRoleService.remove(userRoleWapper);
        }
        return false;
    }

    /**
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @param captcha
     * @description: 修改密码
     * @return: java.lang.Boolean
     * @author yongchen
     * @date: 2021/8/19 10:08
     */
    @Override
    public Boolean updatePassword(Long userId, String oldPassword, String newPassword, String captcha) {
        //验证码验证
        String code = (String) redisTemplate.opsForValue().get(userId.toString());
        if (StringUtils.isBlank(code) || StringUtils.equals(code, captcha)) {
            throw new BraveException("验证码为空或者验证码输入错误");
        }
        //用户信息校验
        SysUser sysUser = this.getById(userId);
        if (sysUser == null) {
            throw new BraveException("用户不存在");
        }
        if (!passwordEncoder.matches(oldPassword, sysUser.getPassWord())) {
            throw new BraveException("修改密码失败，旧密码错误");
        }
        if (passwordEncoder.matches(newPassword, sysUser.getPassWord())) {
            throw new BraveException("修改密码失败，新密码不能与旧密码相同");
        }
        LambdaUpdateWrapper<SysUser> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(SysUser::getId, sysUser.getId())
                .set(SysUser::getPassWord, passwordEncoder.encode(newPassword));
        return this.update(updateWrapper);
    }

    /**
     * @description: 发送验证码至邮箱
     * @param email
     * @return: java.lang.Boolean
     * @author yongchen
     * @date: 2021/8/31 11:48
     */
    @Override
    public Boolean sendCaptcha(String email) {
        Long userId = JwtUtils.getUserId();
        String username = JwtUtils.getUsername();
        //获取验证码
        SpecCaptcha specCaptcha = new SpecCaptcha(100, 40, 6);
        specCaptcha.setCharType(Captcha.TYPE_DEFAULT);
        String text = specCaptcha.text();
        String captcha = text.toUpperCase();
        redisTemplate.opsForValue().set(userId.toString(), captcha, 2, TimeUnit.HOURS);
        //封装邮件信息
        Mail mail = new Mail();
        mail.setRecipient(email);
        mail.setRecipientName(username);
        mail.setContent(captcha);
        mail.setSubject("校验验证码");
        return sendMailService.sendMailVerify(mail);
    }
}
