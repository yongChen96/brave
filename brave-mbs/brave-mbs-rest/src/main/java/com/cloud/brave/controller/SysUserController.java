package com.cloud.brave.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.brave.core.inject.annotation.InjectUser;
import com.cloud.brave.core.inject.entity.BraveUser;
import com.cloud.brave.dto.UserDTO;
import com.cloud.brave.dto.UserDetailsDTO;
import com.cloud.brave.dto.UserInfoDTO;
import com.cloud.brave.entity.SysUser;
import com.cloud.brave.service.SysUserService;
import com.cloud.brave.core.constant.CommonConstants;
import com.cloud.brave.core.exception.BraveException;
import com.cloud.brave.mybatisplus.generator.entity.BaseSuperEntuty;
import com.cloud.brave.mybatisplus.page.PageParam;
import com.cloud.brave.core.result.Result;
import com.cloud.brave.log.annotation.BraveSysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.cloud.brave.core.base.controller.BaseController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;


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
    public Result<Page<SysUser>> page(@RequestBody @Validated(BaseSuperEntuty.OnlyQuery.class) PageParam<SysUser> pp) {
        SysUser data = pp.getData();
        LambdaQueryWrapper<SysUser> pageWapper = new LambdaQueryWrapper<SysUser>();
        pageWapper.eq(SysUser::getDelState, CommonConstants.NOT_DELETED);
        if (StringUtils.isNotBlank(data.getUserName())) {
            pageWapper.eq(SysUser::getUserName, data.getUserName());
        }
        if (StringUtils.isNotBlank(data.getPhone())) {
            pageWapper.eq(SysUser::getPhone, data.getPhone());
        }
        if (StringUtils.isNotBlank(data.getIsLock())){
            pageWapper.eq(SysUser::getIsLock, data.getIsLock());
        }
        if (null != data.getDeptId()) {
            pageWapper.eq(SysUser::getDeptId, data.getDeptId());
        }
        return success(sysUserService.page(pp.getPage(), pageWapper));
    }

    /**
     * @param id
     * @return com.cloud.core.result.Result<com.cloud.brave.entity.SysUser>
     * @Author yongchen
     * @Description 获取用户详细信息
     * @Date 9:27 2021/7/13
     **/
    @GetMapping("/get")
    @BraveSysLog(value = "获取用户详细信息")
    @ApiOperation(value = "获取用户详细信息", notes = "获取用户详细信息")
    public Result<UserDetailsDTO> getDetailsById(@RequestParam Long id) {
        return success(sysUserService.getUserDetailsById(id));
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
//        String phone = braveUser.getPhone();
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
    public Result<Boolean> save(@RequestBody @Validated(BaseSuperEntuty.Save.class) UserDTO userDTO) {
        if (sysUserService.saveNewUser(userDTO)) {
            return success(true);
        }
        return failed("添加新用户信息失败");
    }
    
    /**
     * @Author yongchen
     * @Description 更新用户信息
     * @Date 9:27 2021/7/21
     * @param userDTO
     * @return com.cloud.core.result.Result<java.lang.Boolean>
     **/
    @PostMapping("/update")
    @BraveSysLog(value = "更新用户信息")
    @ApiOperation(value = "更新用户信息", notes = "更新用户信息")
    public Result<Boolean> update(@RequestBody @Validated(BaseSuperEntuty.Update.class) UserDTO userDTO){
        if (sysUserService.updateUser(userDTO)) {
            return success(true);
        }
        return failed("更新用户信息失败");
    }

    /**
     * @Author: yongchen
     * @Description: 修改用户锁定状态
     * @Date: 15:53 2021/6/8
     * @Param: [id]
     * @return: com.cloud.core.result.Result<java.lang.Boolean>
     **/
    @GetMapping("/updateLocakStatus")
    @BraveSysLog(value = "修改用户锁定状态")
    @ApiOperation(value = "修改用户锁定状态", notes = "修改用户锁定状态")
    public Result<Boolean> updateLocakStatus(@RequestParam Long id, @RequestParam String locakStatus) {
        if (sysUserService.locking(id, locakStatus)) {
            return success(true);
        }
        return failed("锁定用户失败");
    }

    /**
     * @param id
     * @return com.cloud.core.result.Result<java.lang.Boolean>
     * @Author yongchen
     * @Description 删除用户信息
     * @Date 9:40 2021/7/19
     **/
    @GetMapping("/del")
    @BraveSysLog(value = "删除用户信息")
    @ApiOperation(value = "删除用户信息", notes = "删除用户信息")
    public Result<Boolean> deleteUser(@RequestParam Long id) {
        LambdaUpdateWrapper<SysUser> delWapper = new LambdaUpdateWrapper<>();
        delWapper.eq(SysUser::getId, id).set(SysUser::getDelState, CommonConstants.DELETED);
        if (sysUserService.update(delWapper)) {
            return success(true);
        }
        return failed("删除用户信息失败");
    }

    /**
     * @param id
     * @return com.cloud.core.result.Result<java.lang.Boolean>
     * @Author yongchen
     * @Description 重置用户密码
     * @Date 10:07 2021/7/19
     **/
    @GetMapping("/resetPwd")
    @BraveSysLog(value = "重置用户密码")
    @ApiOperation(value = "重置用户密码", notes = "重置用户密码")
    public Result<Boolean> resetPassword(@RequestParam Long id) {
        if (sysUserService.resetPassword(id)) {
            return success(true);
        }
        return failed("重置用户密码失败");
    }

    @PostMapping("/uploadAvatar")
    @BraveSysLog(value = "上传头像")
    @ApiOperation(value = "上传头像", notes = "上传头像")
    public Result<Boolean> uploadAvatar(@RequestBody MultipartFile file){

        return success(true);
    }

    @PostMapping("/updatePassword")
    @BraveSysLog(value = "密码修改")
    @ApiOperation(value = "密码修改", notes = "密码修改")
    public Result<Boolean> updatePassword(String oldPassword){
        return success(true);
    }
}
