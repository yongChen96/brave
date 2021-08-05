package com.cloud.brave.auth.handler;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: FormAuthenticationFailureHandler
 * @Description: 表单登录失败处理逻辑
 * @Author: yongchen
 * @Date: 2021/5/26 17:16
 **/
@Slf4j
@Component
public class FormAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.debug("表单登录失败：{}", e.getLocalizedMessage());
        String url = HttpUtil.encodeParams(String.format("/token/login?error=%s", e.getMessage()), CharsetUtil.CHARSET_UTF_8);
        HttpServletResponse servletResponse = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        servletResponse.sendRedirect(url);
    }
}
