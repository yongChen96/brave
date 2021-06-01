package com.cloud.auth.service;

import cn.hutool.core.collection.CollUtil;
import com.cloud.auth.entity.BraveSysUser;
import com.cloud.auth.entity.SysUser;
import com.cloud.auth.mapper.SysUserMapper;
import com.cloud.core.exception.BraveException;
import jdk.internal.dynalink.support.NameCodec;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName: BraveUserDetailsServiceImpl
 * @Description: 用户详细信息
 * @Author: yongchen
 * @Date: 2021/5/24 15:40
 **/
@Service
public class BraveUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<String> strings = new ArrayList<>();
        strings.add("ROLE_ADMIN");
        strings.add("ROLE_USER");
        SysUser sysUser = sysUserMapper.selectUserByPhone(s);
        System.out.println(sysUser);
        if (null == sysUser) {
            throw new UsernameNotFoundException("用户不存在");
        } else if (sysUser.getIsEffective() == "0") {
            throw new DisabledException("该账户也失效");
        }
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils
                .createAuthorityList(strings.toArray(new String[0]));
        return new BraveSysUser(sysUser.getId(), sysUser.getNickName(), sysUser.getUserName(), sysUser.getPassWord(),
                true, true, true, true, authorities);
    }
}
