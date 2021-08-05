package com.cloud.brave.api.fallback;

import com.cloud.brave.dto.UserInfoDTO;
import com.cloud.brave.api.fegin.SysUserFeignService;
import com.cloud.brave.core.result.Result;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName: SysUserFeignFallBack
 * @Description: 用户信息接口待用异常默认返回
 * @Author: yongchen
 * @Date: 2021/6/3 16:53
 **/
@Slf4j
@Component
public class SysUserFeignFallBack implements SysUserFeignService {

    @Setter
    private Throwable cause;

    /**
     * @Author: yongchen
     * @Description: 获取用户信息
     * @Date: 9:52 2021/6/4
     * @Param: [username]
     * @return: com.cloud.core.result.Result<com.cloud.brave.dto.UserInfoDTO>
     **/
    @Override
    public Result<UserInfoDTO> getUserInfo(String username) {
        log.error("feign 查询用户{}信息失败:{}", username, cause);
        return null;
    }
}
