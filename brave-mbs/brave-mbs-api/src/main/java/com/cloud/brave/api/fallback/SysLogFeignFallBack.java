package com.cloud.brave.api.fallback;

import com.cloud.brave.api.fegin.SysLogFeignService;
import com.cloud.brave.entity.SysLog;
import com.cloud.brave.core.result.Result;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * @ClassName: SysLogFeginFallBack
 * @Description: 日志接口待用异常默认返回
 * @Author: yongchen
 * @Date: 2021/6/4 10:20
 **/
@Slf4j
@Component
public class SysLogFeignFallBack implements SysLogFeignService {

    @Setter
    private Throwable cause;

    @Override
    public Result<Boolean> saveSysLog(SysLog sysLog) {
        log.error("feign调用保存操作日志信息:{}失败原因:{}", sysLog, cause);
        return null;
    }
}
