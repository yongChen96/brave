package com.cloud.api.fegin;

import com.cloud.brave.dto.UserInfoDTO;
import com.cloud.api.factory.SysUserFeignFallBackFactory;
import com.cloud.core.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName: SysUserFeignService
 * @Description: 用户信息调用服务
 * @Author: yongchen
 * @Date: 2021/6/3 16:32
 **/
@FeignClient(contextId = "sysUserFeginService", name = "brave-mbs-rest", fallbackFactory = SysUserFeignFallBackFactory.class)
public interface SysUserFeignService {

    /**
     * @Author: yongchen
     * @Description: 获取用户信息
     * @Date: 9:52 2021/6/4
     * @Param: [username]
     * @return: com.cloud.core.result.Result<com.cloud.brave.dto.UserInfoDTO>
     **/
    @GetMapping(value = "/sysUser/getUserInfo")
    Result<UserInfoDTO> getUserInfo(@RequestParam("username") String username);
}
