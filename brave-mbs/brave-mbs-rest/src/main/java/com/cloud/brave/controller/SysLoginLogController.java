package com.cloud.brave.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.brave.entity.SysLoginLog;
import com.cloud.brave.service.SysLoginLogService;
import com.cloud.brave.core.mybatisplus.entity.BaseSuperEntuty;
import com.cloud.brave.core.mybatisplus.page.PageParam;
import com.cloud.brave.core.result.Result;
import com.cloud.brave.log.annotation.BraveSysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.cloud.brave.core.base.controller.BaseController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yongchen
 * @since 2021-08-03
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/sysLoginLog")
@Api(value = "SysLoginLogController", tags = "登录日志前端控制器")
public class SysLoginLogController extends BaseController {

    private final SysLoginLogService sysLoginLogService;

    /**
     * @param pp
     * @return com.cloud.core.result.Result<com.baomidou.mybatisplus.core.metadata.IPage < com.cloud.brave.entity.SysLoginLog>>
     * @Author yongchen
     * @Description 分页查询登录日志
     * @Date 17:06 2021/8/3
     **/
    @PostMapping("/page")
    @BraveSysLog(value = "分页查询登录日志")
    @ApiOperation(value = "分页查询登录日志", notes = "分页查询登录日志")
    public Result<IPage<SysLoginLog>> page(@RequestBody @Validated(BaseSuperEntuty.OnlyQuery.class) PageParam<SysLoginLog> pp) {
        LambdaQueryWrapper<SysLoginLog> pageWapper = new LambdaQueryWrapper<>();
        Page<SysLoginLog> page = sysLoginLogService.page(pp.getPage(), pageWapper);
        return success(page);
    }

    /**
     * @Author yongchen
     * @Description 保存登录日志
     * @Date 17:08 2021/8/3
     * @param sysLoginLog
     * @return com.cloud.core.result.Result<java.lang.Boolean>
     **/
    @PostMapping("/save")
    @ApiOperation(value = "保存登录日志", notes = "保存登录日志")
    public Result<Boolean> save(@RequestBody @Validated(BaseSuperEntuty.Save.class) SysLoginLog sysLoginLog) {
        if (sysLoginLogService.save(sysLoginLog)) {
            return success(true);
        }
        return failed("保存登录日志信息失败");
    }
}
