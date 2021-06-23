package com.cloud.auth.service;

import cn.hutool.core.util.ArrayUtil;
import com.cloud.api.fegin.SysUserFeignService;
import com.cloud.auth.entity.BraveSysUser;
import com.cloud.brave.dto.UserInfoDTO;
import com.cloud.brave.entity.SysUser;
import com.cloud.core.constant.CommonConstants;
import com.cloud.core.exception.BraveException;
import com.cloud.core.result.Result;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName: BraveUserDetailsServiceImpl
 * @Description: 用户详细信息
 * @Author: yongchen
 * @Date: 2021/5/24 15:40
 **/
@Service
@RequiredArgsConstructor
public class BraveUserDetailsServiceImpl implements UserDetailsService {

    private final SysUserFeignService sysUserFeignService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Result<UserInfoDTO> result = sysUserFeignService.getUserInfo(s);
        if (null == result || null == result.getData()) {
            throw new BraveException("用户不存在");
        }
        UserInfoDTO userInfo = result.getData();
        HashSet<String> hashSet = new HashSet<>();
        if (ArrayUtil.isNotEmpty(userInfo.getRoles())) {
            //获取用户角色信息
            Arrays.stream(userInfo.getRoles()).forEach(role -> hashSet.add(role.toString()));
            //获取资源信息
            hashSet.addAll(Arrays.asList(userInfo.getPermissions()));
        }
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(hashSet.toArray(new String[0]));
        SysUser sysUser = userInfo.getSysUser();
        return new BraveSysUser(sysUser.getId(),
                sysUser.getPhone(),
                sysUser.getUserName(),
                sysUser.getPassWord(),
                StringUtils.equals(CommonConstants.IS_LOCK_NO, sysUser.getIsLock()),
                true,
                true,
                true,
                authorities);
    }
}
