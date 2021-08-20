package com.cloud.brave.api.factory;

import com.cloud.brave.api.fallback.SysFileFeignFallBack;
import com.cloud.brave.api.fegin.SysFileFeignService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author yongchen
 * @description: SysFileFeignFallBackService
 * @date 2021/8/19 10:27
 */
@Component
public class SysFileFeignFallBackFactory implements FallbackFactory<SysFileFeignService> {

    @Override
    public SysFileFeignService create(Throwable cause) {
        SysFileFeignFallBack fallBack = new SysFileFeignFallBack();
        fallBack.setCause(cause);
        return fallBack;
    }
}
