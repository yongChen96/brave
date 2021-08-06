package com.cloud.brave.auth.listener;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.cloud.brave.api.fegin.SysLoginLogFeignService;
import com.cloud.brave.core.constant.CommonConstants;
import com.cloud.brave.entity.SysLoginLog;
import com.cloud.brave.log.event.BraveLoginLogEvent;
import com.cloud.brave.log.utils.IPUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author admin
 * @version 1.0
 * @description: 登录监控
 * @date 2021/8/5 16:47
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AuthEventListener {

    private final ApplicationContext applicationContext;
    private final SysLoginLogFeignService sysLoginLogFeignService;
    private static final String className = "org.springframework.security.authentication.UsernamePasswordAuthenticationToken";

    /**
     * @param event
     * @return void
     * @Author yongchen
     * @Description 登录成功监听
     * @Date 16:49 2021/8/5
     **/
    @EventListener
    public void successLogin(AuthenticationSuccessEvent event) {
        log.info("登陆成功");
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        // 只需要监听登录事件（UsernamePasswordAuthenticationToken）及token验证事件源（OAuth2Authentication）
        if (!StringUtils.equals(className, event.getSource().getClass().getName())) {
            return;
        }
        if (null != event.getAuthentication().getDetails()) {
            Object source = event.getSource();
            JSONObject jsonObject = JSONUtil.parseObj(source);
            Object principal = jsonObject.get(CommonConstants.PRINCIPAL);
            String name = (String) JSONUtil.parseObj(principal).get(CommonConstants.PHONE);
            if (StringUtils.equals(CommonConstants.CLIENT_ID, name)){
                return;
            }
            SysLoginLog loginLog = new SysLoginLog();
            loginLog.setUserName(name);
            loginLog.setStatus("0");
            loginLog.setMsg("登录成功");
            //访问地址信息
            String ipAddr = IPUtils.getIpAddr(request);
            loginLog.setIpAddr(ipAddr);
            loginLog.setCityAddr(IPUtils.getCityInfo(ipAddr));
            //获取访问设备、系统、浏览器信息
            UserAgent parse = UserAgentUtil.parse(request.getHeader(CommonConstants.USER_AGENT));
            if (parse.isMobile()) {
                loginLog.setDeviceType(parse.getPlatform().getName());
            }else {
                loginLog.setDeviceType(CommonConstants.COMPUTER);
            }
            loginLog.setOperateSystem(parse.getOs().toString());
            loginLog.setBrowser(String.format("%s %s", parse.getBrowser().toString(), parse.getVersion()));
            // applicationContext.publishEvent(new BraveLoginLogEvent(loginLog));
            asyncSaveSysLog(loginLog);
        }
    }

    /**
     * @param event
     * @return void
     * @Author yongchen
     * @Description 用户名密码错误失败监听
     * @Date 16:56 2021/8/5
     **/
    @EventListener
    public void failedLogin(AuthenticationFailureBadCredentialsEvent event) {
        log.info("登录失败");
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        SysLoginLog loginLog = new SysLoginLog();
        Object source = event.getSource();
        JSONObject jsonObject = JSONUtil.parseObj(source);
        String name = (String) jsonObject.get(CommonConstants.PRINCIPAL);
        loginLog.setUserName(name);
        loginLog.setStatus("1");
        loginLog.setMsg("用户名或密码错误");
        //访问地址信息
        String ipAddr = IPUtils.getIpAddr(request);
        loginLog.setIpAddr(ipAddr);
        loginLog.setCityAddr(IPUtils.getCityInfo(ipAddr));
        //获取访问设备、系统、浏览器信息
        UserAgent parse = UserAgentUtil.parse(request.getHeader(CommonConstants.USER_AGENT));
        if (parse.isMobile()) {
            loginLog.setDeviceType(parse.getPlatform().getName());
        }else {
            loginLog.setDeviceType(CommonConstants.COMPUTER);
        }
        loginLog.setOperateSystem(parse.getOs().toString());
        loginLog.setBrowser(String.format("%s %s", parse.getBrowser().toString(), parse.getVersion()));
        // applicationContext.publishEvent(new BraveLoginLogEvent(loginLog));
        asyncSaveSysLog(loginLog);
    }

    @Async
    public void asyncSaveSysLog(SysLoginLog loginLog) {
        log.info("[{}], [{}]", "保存登录日志", JSON.toJSONString(loginLog));
        if (null != loginLog){
            sysLoginLogFeignService.saveLoginLog(loginLog);
        }
    }

}
