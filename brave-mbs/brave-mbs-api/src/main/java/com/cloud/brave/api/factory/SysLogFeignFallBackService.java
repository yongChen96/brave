package com.cloud.brave.api.factory;

import com.cloud.brave.api.fallback.SysLogFeignFallBack;
import com.cloud.brave.api.fegin.SysLogFeignService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName: SysLogFeignFallBackService
 * @Description: SysLogFeignFallBack工厂
 * @Author: yongchen
 * @Date: 2021/6/4 10:22
 **/
@Component
public class SysLogFeignFallBackService implements FallbackFactory<SysLogFeignService> {

    @Override
    public SysLogFeignService create(Throwable cause) {
        SysLogFeignFallBack fallBack = new SysLogFeignFallBack();
        fallBack.setCause(cause);
        return fallBack;
    }
}
