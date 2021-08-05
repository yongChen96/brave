package com.cloud.brave.log.aspect;

import cn.hutool.core.convert.Convert;
import com.cloud.brave.entity.SysLoginLog;
import com.cloud.brave.core.constant.AuthConstants;
import com.cloud.brave.core.result.Result;
import com.cloud.brave.log.annotation.BraveLoginLog;
import com.cloud.brave.log.event.BraveLoginLogEvent;
import com.cloud.brave.log.utils.IPUtils;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author admin
 * @version 1.0
 * @description: 登录日志
 * @date 2021/8/3 17:12
 */
@Slf4j
@Aspect
@Component
public class LoginLogAspect {

    @Resource
    private ApplicationContext applicationContext;

    @Pointcut("@annotation(com.cloud.brave.log.annotation.BraveLoginLog)")
    private void loginLogAspect() {
    }

    @AfterReturning(pointcut = "loginLogAspect()", returning = "res")
    public Object doAfterReturning(JoinPoint joinPoint, Object res) throws Throwable {
        SysLoginLog sysLoginLog = new SysLoginLog();
        // 获取请求信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 刷新token不记录
        String grantType = request.getParameter(AuthConstants.GRANT_TYPE_KEY);
        if (grantType.equals(AuthConstants.REFRESH_TOKEN)) {
            return res;
        }
        String username = request.getParameter(AuthConstants.USER_NAME_KEY);
        sysLoginLog.setUserName(username);
        // 获取接口描述信息
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 方法描述
        String value = signature.getMethod().getAnnotation(BraveLoginLog.class).value();
        // 客户端请求IP（注意：如果使用Nginx代理需配置）
        String clientIP = IPUtils.getIpAddr(request);
        sysLoginLog.setIpAddr(clientIP);
        // IP对应的城市信息
        String region = IPUtils.getCityInfo(clientIP);
        sysLoginLog.setCityAddr(region);
        //获取访问浏览器信息
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("user-agent"));
        String clientType = userAgent.getOperatingSystem().getDeviceType().toString();
        sysLoginLog.setDeviceType(clientType);
        String os = userAgent.getOperatingSystem().getName();
        sysLoginLog.setOperateSystem(os);
        String browser = userAgent.getBrowser().toString();
        sysLoginLog.setBrowser(browser);
        if (null != res) {
            Result convert = Convert.convert(Result.class, res);
            if (convert.getCode() == 0) {
                sysLoginLog.setStatus("0");
            } else {
                sysLoginLog.setStatus("1");
            }
            String s = StringUtils.substring(convert.toString(), 0, 65535);
            sysLoginLog.setMsg(s);
        }
        applicationContext.publishEvent(new BraveLoginLogEvent(sysLoginLog));
        return sysLoginLog;
    }

    @AfterThrowing(pointcut = "loginLogAspect()", throwing = "e")
    public Object doAfterThrowing(JoinPoint joinPoint, Exception e) throws Throwable {
        SysLoginLog loginLog = new SysLoginLog();
        // 获取请求信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String username = request.getParameter(AuthConstants.USER_NAME_KEY);
        loginLog.setUserName(username);
        loginLog.setStatus("1");
        // 客户端请求IP（注意：如果使用Nginx代理需配置）
        String clientIP = IPUtils.getIpAddr(request);
        loginLog.setIpAddr(clientIP);
        // IP对应的城市信息
        String region = IPUtils.getCityInfo(clientIP);
        loginLog.setCityAddr(region);
        //获取访问浏览器信息
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("user-agent"));
        String clientType = userAgent.getOperatingSystem().getDeviceType().toString();
        loginLog.setDeviceType(clientType);
        String os = userAgent.getOperatingSystem().getName();
        loginLog.setOperateSystem(os);
        String browser = userAgent.getBrowser().toString();
        loginLog.setBrowser(browser);
        loginLog.setMsg(e.getMessage());
        applicationContext.publishEvent(new BraveLoginLogEvent(loginLog));
        return loginLog;
    }
}
