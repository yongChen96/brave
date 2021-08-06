package com.cloud.brave.auth.service;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.cloud.brave.api.fegin.SysUserFeignService;
import com.cloud.brave.auth.entity.BraveSysUser;
import com.cloud.brave.dto.UserInfoDTO;
import com.cloud.brave.entity.SysLoginLog;
import com.cloud.brave.entity.SysUser;
import com.cloud.brave.core.constant.CommonConstants;
import com.cloud.brave.core.exception.BraveException;
import com.cloud.brave.core.result.Result;
import com.cloud.brave.log.event.BraveLoginLogEvent;
import com.cloud.brave.log.utils.IPUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
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
    private final ApplicationContext applicationContext;

    @Override
    public UserDetails loadUserByUsername(String s) {
        if (StringUtils.isBlank(s)) {
            recordLoginLog(null, "1", "用户/密码必须填写");
            throw new BraveException("用户/密码必须填写");
        }
        Result<UserInfoDTO> result = sysUserFeignService.getUserInfo(s);
        if (null == result || null == result.getData()) {
            recordLoginLog(s, "1", "登录用户不存在");
            throw new BraveException("登录用户不存在");
        }
        UserInfoDTO userInfo = result.getData();
        if (StringUtils.equals(CommonConstants.IS_LOCK_YES, userInfo.getSysUser().getIsLock())){
            recordLoginLog(s, "1", "登录用户被锁定");
            throw new BraveException("登录用户被锁定");
        }
        HashSet<String> hashSet = new HashSet<>();
        Long[] roles = userInfo.getRoles();
        if (ArrayUtil.isNotEmpty(roles)) {
            //获取用户角色信息
            Arrays.stream(roles).forEach(role -> hashSet.add(role.toString()));
            //获取资源信息
            hashSet.addAll(Arrays.asList(userInfo.getPermissions()));
        }
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(hashSet.toArray(new String[0]));
        SysUser sysUser = userInfo.getSysUser();
        return new BraveSysUser(sysUser.getId(),
                sysUser.getPhone(),
                sysUser.getUserName(),
                sysUser.getPassWord(),
                sysUser.getDeptId(),
                roles,
                StringUtils.equals(CommonConstants.IS_LOCK_NO, sysUser.getIsLock()),
                true,
                true,
                true,
                authorities);
    }

    /**
     * @param username
     * @param status
     * @param msg
     * @return void
     * @Author yongchen
     * @Description 保存登录日志信息
     * @Date 10:48 2021/8/4
     **/
    public void recordLoginLog(String username, String status, String msg) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        SysLoginLog loginLog = new SysLoginLog();
        loginLog.setUserName(username);
        loginLog.setStatus(status);
        loginLog.setMsg(msg);
        //访问IP、城市信息
        String ipAddr = IPUtils.getIpAddr(request);
        String cityAddr = IPUtils.getCityInfo(ipAddr);
        loginLog.setIpAddr(ipAddr);
        loginLog.setCityAddr(cityAddr);
        //获取访问设备、系统、浏览器信息
        UserAgent parse = UserAgentUtil.parse(request.getHeader(CommonConstants.USER_AGENT));
        if (parse.isMobile()) {
            loginLog.setDeviceType(parse.getPlatform().getName());
        }else {
            loginLog.setDeviceType(CommonConstants.COMPUTER);
        }
        loginLog.setOperateSystem(parse.getOs().toString());
        loginLog.setBrowser(String.format("%s %s", parse.getBrowser().toString(), parse.getVersion()));
        applicationContext.publishEvent(new BraveLoginLogEvent(loginLog));
    }
}
