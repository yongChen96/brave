package com.cloud.brave.controller;

import com.cloud.brave.core.base.controller.BaseController;
import com.cloud.brave.core.exception.BraveException;
import com.cloud.brave.core.result.Result;
import com.cloud.brave.dto.UserInfoDTO;
import com.cloud.brave.service.SysUserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author admin
 * @version 1.0
 * @description: 登录控制器
 * @date 2021/8/9 9:39
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController extends BaseController {

    private final SysUserService sysUserService;

    /**
     * @Author: yongchen
     * @Description: 获取用户信息(登录时获取用户信息)
     * @Date: 14:08 2021/6/4
     * @Param: [username]
     * @return: com.cloud.core.result.Result<UserInfoDTO>
     **/
    @GetMapping(value = "/getUserDetail")
    @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    public Result<UserInfoDTO> getUserDetail(@RequestParam(value = "username") String username) {
        UserInfoDTO userInfo = sysUserService.getUserInfo(username);
        if (null == userInfo) {
            throw new BraveException("获取用户信息失败");
        }
        return success(userInfo);
    }
}
