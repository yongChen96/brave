package com.cloud.brave.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.brave.entity.SysLoginLog;
import com.cloud.brave.service.SysLoginLogService;
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
        SysLoginLog data = pp.getData();
        if (StringUtils.isNotBlank(data.getUserName())){
            pageWapper.eq(SysLoginLog::getUserName, data.getUserName());
        }
        if (StringUtils.isNotBlank(data.getStatus())){
            pageWapper.eq(SysLoginLog::getStatus, data.getStatus());
        }
        pageWapper.orderByDesc(SysLoginLog::getCreateTime);
        Page<SysLoginLog> page = sysLoginLogService.page(pp.getPage(), pageWapper);
        return success(page);
    }

    /**
     * @param id
     * @return com.cloud.brave.core.result.Result<com.cloud.brave.entity.SysLoginLog>
     * @Author yongchen
     * @Description 获取日志详情
     * @Date 9:58 2021/8/9
     **/
    @GetMapping("/get")
    @ApiOperation(value = "获取日志详情", notes = "获取日志详情")
    public Result<SysLoginLog> get(@RequestParam Long id) {
        return success(sysLoginLogService.getById(id));
    }

    /**
     * @param sysLoginLog
     * @return com.cloud.core.result.Result<java.lang.Boolean>
     * @Author yongchen
     * @Description 保存登录日志
     * @Date 17:08 2021/8/3
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
