package com.cloud.api.factory;

import com.cloud.api.fallback.SysUserFeignFallBack;
import com.cloud.api.fegin.SysUserFeignService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName: SysUserFeignFallBackFactory
 * @Description: SysUserFeignFallBack工厂
 * @Author: yongchen
 * @Date: 2021/6/4 9:23
 **/
@Component
public class SysUserFeignFallBackFactory implements FallbackFactory<SysUserFeignService> {

    @Override
    public SysUserFeignService create(Throwable cause) {
        SysUserFeignFallBack fallBack = new SysUserFeignFallBack();
        fallBack.setCause(cause);
        return fallBack;
    }
}
