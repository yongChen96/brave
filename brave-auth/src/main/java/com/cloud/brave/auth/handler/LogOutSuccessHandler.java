package com.cloud.brave.auth.handler;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: LoginOutHandler
 * @Description: 推出处理
 * @Author: yongchen
 * @Date: 2021/5/26 17:24
 **/
@Component
public class LogOutSuccessHandler implements LogoutSuccessHandler {

    private static final String REDIRECT_URL = "redirect_url";

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        // 获取参数中包含的回调地址
        String redirectUrl = httpServletRequest.getParameter(REDIRECT_URL);
        if (StringUtils.isNotBlank(redirectUrl)) {
            httpServletResponse.sendRedirect(redirectUrl);
        } else {
            // 默认返回地址
            String referer = httpServletRequest.getHeader(HttpHeaders.REFERER);
            httpServletResponse.sendRedirect(referer);
        }
    }
}
