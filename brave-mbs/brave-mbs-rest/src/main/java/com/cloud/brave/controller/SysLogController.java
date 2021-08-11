package com.cloud.brave.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.brave.entity.SysLog;
import com.cloud.brave.service.SysLogService;
import com.cloud.brave.mybatisplus.page.PageParam;
import com.cloud.brave.core.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.cloud.brave.core.base.controller.BaseController;

/**
 * <p>
 * 系统日志 前端控制器
 * </p>
 *
 * @author yongchen
 * @since 2021-06-07
 */
@RestController
@RequestMapping("/sysLog")
@RequiredArgsConstructor
@Api(value = "SysLogController", tags = "系统日志前端控制器")
public class SysLogController extends BaseController {

    private final SysLogService sysLogService;

    /**
     * @Author: yongchen
     * @Description: 分页获取系统操作日志信息
     * @Date: 10:50 2021/6/7
     * @Param: [pp]
     * @return: com.cloud.core.result.Result<com.baomidou.mybatisplus.core.metadata.IPage<com.cloud.brave.entity.SysLog>>
     **/
    @PostMapping("/page")
    @ApiOperation(value = "分页获取系统操作日志信息", notes = "分页获取系统操作日志信息")
    public Result<IPage<SysLog>> page(@RequestBody @Validated PageParam<SysLog> pp){
        LambdaQueryWrapper<SysLog> queryWrapper = new LambdaQueryWrapper<>();
        SysLog data = pp.getData();
        if (StringUtils.isNotBlank(data.getCreateName())){
            queryWrapper.eq(SysLog::getCreateName, data.getCreateName());
        }
        if (StringUtils.isNotBlank(data.getType())){
            queryWrapper.eq(SysLog::getType, data.getType());
        }
        queryWrapper.orderByDesc(SysLog::getCreateTime);
        Page<SysLog> page = sysLogService.page(pp.getPage(), queryWrapper);
        return success(page);
    }

    /**
     * @param id
     * @return com.cloud.brave.core.result.Result<com.cloud.brave.entity.SysLog>
     * @Author yongchen
     * @Description 获取日志详情
     * @Date 9:58 2021/8/9
     **/
    @GetMapping("/get")
    @ApiOperation(value = "获取日志详情", notes = "获取日志详情")
    public Result<SysLog> get(@RequestParam Long id) {
        return success(sysLogService.getById(id));
    }

    /**
     * @Author: yongchen
     * @Description: 保存系统操作日志信息
     * @Date: 10:45 2021/6/7
     * @Param: [sysLog]
     * @return: com.cloud.core.result.Result<java.lang.Boolean>
     **/
    @PostMapping("/save")
    @ApiOperation(value = "保存系统操作日志信息",notes = "保存系统操作日志信息")
    public Result<Boolean> save(@RequestBody SysLog sysLog){
        boolean save = sysLogService.save(sysLog);
        if (save){
            return success(null);
        }
        return failed("系统日志信息保存失败");
    }
}
