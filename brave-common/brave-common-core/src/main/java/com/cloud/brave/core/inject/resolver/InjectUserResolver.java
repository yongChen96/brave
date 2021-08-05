package com.cloud.brave.core.inject.resolver;

import cn.hutool.core.bean.BeanUtil;
import com.cloud.brave.core.inject.entity.BraveUser;
import com.cloud.brave.core.inject.annotation.InjectUser;
import com.cloud.brave.core.utils.JwtUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @ClassName: InjectUserResolver
 * @Description: 统一获取当前登录用户
 * @Author: yongchen
 * @Date: 2021/5/26 14:00
 **/
public class InjectUserResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(InjectUser.class) &&
                methodParameter.getParameterType().isAssignableFrom(BraveUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {
        Object object = JwtUtils.getObject();
        return BeanUtil.copyProperties(object, BraveUser.class);
    }
}
