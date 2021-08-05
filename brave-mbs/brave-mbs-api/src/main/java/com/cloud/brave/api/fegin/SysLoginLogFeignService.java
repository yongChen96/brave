package com.cloud.brave.api.fegin;

import com.cloud.brave.api.factory.SysLoginLogFeignFallBackService;
import com.cloud.brave.entity.SysLoginLog;
import com.cloud.brave.core.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @ClassName: SysLogFeginService
 * @Description: 日志调用服务
 * @Author: yongchen
 * @Date: 2021/6/4 10:02
 **/
@FeignClient(contextId = "sysLoginLogFeginSerivce", name = "brave-mbs-rest", fallbackFactory = SysLoginLogFeignFallBackService.class)
public interface SysLoginLogFeignService {

    /**
     * @Author: yongchen
     * @Description: 保存系统操作日志信息
     * @Date: 10:45 2021/6/7
     * @Param: [sysLog]
     * @return: com.cloud.core.result.Result<java.lang.Boolean>
     **/
    @PostMapping(value = "/sysLoginLog/save")
    Result<Boolean> saveLoginLog(@RequestBody SysLoginLog loginLog);
}
