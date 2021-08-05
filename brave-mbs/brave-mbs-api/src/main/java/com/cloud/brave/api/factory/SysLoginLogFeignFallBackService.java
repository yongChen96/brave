package com.cloud.brave.api.factory;

import com.cloud.brave.api.fallback.SysSysLoginLogFeignFallBack;
import com.cloud.brave.api.fegin.SysLoginLogFeignService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName: LoginLogFeignFallBackService
 * @Description: LoginLogFeignService工厂
 * @Author: yongchen
 * @Date: 2021/6/4 10:22
 **/
@Component
public class SysLoginLogFeignFallBackService implements FallbackFactory<SysLoginLogFeignService> {

    @Override
    public SysLoginLogFeignService create(Throwable cause) {
        SysSysLoginLogFeignFallBack fallBack = new SysSysLoginLogFeignFallBack();
        fallBack.setCause(cause);
        return fallBack;
    }
}
