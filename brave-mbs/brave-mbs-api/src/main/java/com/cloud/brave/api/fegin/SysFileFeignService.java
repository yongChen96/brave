package com.cloud.brave.api.fegin;

import com.cloud.brave.api.factory.SysFileFeignFallBackFactory;
import com.cloud.brave.core.constant.ServiceNameConstants;
import com.cloud.brave.core.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yongchen
 * @description: 附件服务
 * @date 2021/8/19 10:21
 */
@FeignClient(contextId = "sysFileFeignService", path = "/file", name = ServiceNameConstants.MINIO, fallbackFactory = SysFileFeignFallBackFactory.class)
public interface SysFileFeignService {

    /**
     * @description: 上传附件
     * @param bucketName
     * @param file
     * @return: com.cloud.brave.core.result.Result<java.lang.String>
     * @author yongchen
     * @date: 2021/8/19 10:52
     */
    @PostMapping(value = "/upload")
    Result<String> uploadFile(@RequestParam(value = "bucketName") String bucketName, @RequestBody MultipartFile file);
}
