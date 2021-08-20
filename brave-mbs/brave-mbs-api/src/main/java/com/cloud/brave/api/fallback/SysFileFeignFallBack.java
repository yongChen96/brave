package com.cloud.brave.api.fallback;

import com.cloud.brave.api.fegin.SysFileFeignService;
import com.cloud.brave.core.result.Result;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yongchen
 * @description: 附件服务调用异常默认返回
 * @date 2021/8/19 10:23
 */
@Slf4j
@Component
public class SysFileFeignFallBack implements SysFileFeignService {

    @Setter
    private Throwable cause;

    @Override
    public Result<String> uploadFile(String bucketName, MultipartFile file) {
        log.error("feign调用附件上传失败,失败原因:{}", cause);
        return null;
    }
}
