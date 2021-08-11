package com.cloud.brave.api.factory;

import com.cloud.brave.api.fallback.LoginUserDetailFeignFallBack;
import com.cloud.brave.api.fegin.LoginUserDetailFeignService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName: SysUserFeignFallBackFactory
 * @Description: SysUserFeignFallBack工厂
 * @Author: yongchen
 * @Date: 2021/6/4 9:23
 **/
@Component
public class LoginUserDetailFeignFallBackFactory implements FallbackFactory<LoginUserDetailFeignService> {

    @Override
    public LoginUserDetailFeignService create(Throwable cause) {
        LoginUserDetailFeignFallBack fallBack = new LoginUserDetailFeignFallBack();
        fallBack.setCause(cause);
        return fallBack;
    }
}
