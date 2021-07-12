package com.cloud.brave.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.brave.dto.UserDTO;
import com.cloud.brave.dto.UserInfoDTO;
import com.cloud.brave.entity.SysUser;
import com.cloud.brave.service.SysUserService;
import com.cloud.core.exception.BraveException;
import com.cloud.core.mybatisplus.page.PageParam;
import com.cloud.core.result.Result;
import com.cloud.log.annotation.BraveSysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.cloud.core.base.controller.BaseController;


/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author yongchen
 * @since 2021-06-03
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/sysUser")
@Api(value = "SysUserController", tags = "用户信息表前端控制器")
public class SysUserController extends BaseController {

    private final SysUserService sysUserService;

    /**
     * @Author: yongchen
     * @Description: 分页获取用户信息
     * @Date: 10:54 2021/6/7
     * @Param: [pp]
     * @return: com.cloud.core.result.Result<com.baomidou.mybatisplus.core.metadata.IPage < com.cloud.brave.entity.SysUser>>
     **/
    @PostMapping("/page")
    @BraveSysLog(value = "分页获取用户信息")
    @ApiOperation(value = "分页获取用户信息", notes = "分页获取用户信息")
    public Result<IPage<SysUser>> page(@RequestBody @Validated PageParam<SysUser> pp) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        Page<SysUser> page = sysUserService.page(pp.getPage(), queryWrapper);
        return success(page);
    }

    /**
     * @Author: yongchen
     * @Description: 获取用户信息
     * @Date: 14:08 2021/6/4
     * @Param: [username]
     * @return: com.cloud.core.result.Result<UserInfoDTO>
     **/
    @GetMapping(value = "/getUserInfo")
    @BraveSysLog(value = "获取用户信息")
    @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    public Result<UserInfoDTO> getUserInfo(@RequestParam(value = "username") String username) {
        UserInfoDTO userInfo = sysUserService.getUserInfo(username);
        if (null == userInfo) {
            throw new BraveException("获取用户信息失败");
        }
        return success(userInfo);
    }

    /**
     * @Author: yongchen
     * @Description: 获取当前登录用户信息
     * @Date: 14:06 2021/6/7
     * @Param: [user]
     * @return: com.cloud.core.result.Result<com.cloud.brave.dto.UserInfoDTO>
     **/
    @GetMapping("/info")
    @BraveSysLog(value = "获取当前登录用户信息")
    @ApiOperation(value = "获取当前登录用户信息", notes = "获取当前登录用户信息")
    public Result<UserInfoDTO> info() {
        String phone = "18311540852";
        if (StringUtils.isNotBlank(phone)) {
            UserInfoDTO userInfo = sysUserService.getUserInfo(phone);
            if (null == userInfo) {
                throw new BraveException("获取用户信息失败");
            }
            return success(userInfo);
        }
        return failed("获取用户信息失败");
    }

    /**
     * @Author: yongchen
     * @Description: 添加新用户信息
     * @Date: 11:02 2021/6/7
     * @Param: [userDTO]
     * @return: com.cloud.core.result.Result<java.lang.Boolean>
     **/
    @PostMapping("/save")
    @BraveSysLog(value = "添加新用户信息")
    @ApiOperation(value = "添加新用户信息", notes = "添加新用户信息")
    public Result<Boolean> save(@RequestBody @Validated UserDTO userDTO) {
        Boolean save = sysUserService.saveNewUser(userDTO);
        if (save) {
            return success(true);
        }
        return failed("添加新用户信息失败");
    }

    /**
     * @Author: yongchen
     * @Description: 锁定用户
     * @Date: 15:53 2021/6/8
     * @Param: [id]
     * @return: com.cloud.core.result.Result<java.lang.Boolean>
     **/
    @PostMapping("/locking")
    @BraveSysLog(value = "锁定用户")
    @ApiOperation(value = "锁定用户", notes = "锁定用户")
    public Result<Boolean> locking(@RequestParam Long id) {
        if (sysUserService.locking(id)) {
            return success(true);
        }
        return failed("锁定用户失败");
    }

    /**
     * @Author: yongchen
     * @Description: 用户解锁
     * @Date: 15:58 2021/6/8
     * @Param: [id]
     * @return: com.cloud.core.result.Result<java.lang.Boolean>
     **/
    @PostMapping("/unlock")
    @BraveSysLog(value = "用户解锁")
    @ApiOperation(value = "用户解锁", notes = "用户解锁")
    public Result<Boolean> unlock(@RequestParam Long id) {
        if (sysUserService.unlock(id)) {
            return success(true);
        }
        return failed("用户解锁失败");
    }
}
