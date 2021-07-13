package com.cloud.brave.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cloud.brave.entity.SysDict;
import com.cloud.brave.service.SysDictService;
import com.cloud.core.constant.CommonConstants;
import com.cloud.core.result.Result;
import com.cloud.log.annotation.BraveSysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.cloud.core.base.controller.BaseController;

import java.util.List;

/**
 * <p>
 * 字典数据表 前端控制器
 * </p>
 *
 * @author yongchen
 * @since 2021-07-13
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/sysDict")
@Api(value = "SysDictController", tags = "字典数据表")
public class SysDictController extends BaseController {

    private final SysDictService sysDictService;

    /**
     * @Author yongchen
     * @Description 根据字典类型获取字典
     * @Date 10:46 2021/7/13
     * @param dictType
     * @return com.cloud.core.result.Result<java.util.List<com.cloud.brave.entity.SysDict>>
     **/
    @GetMapping(value = "/type/{dictType}")
    @BraveSysLog(value = "根据字典类型获取字典")
    @ApiOperation(value = "根据字典类型获取字典", notes = "根据字典类型获取字典")
    public Result<List<SysDict>> findDictByType(@PathVariable String dictType){
        LambdaQueryWrapper<SysDict> dictWrapper = new LambdaQueryWrapper<>();
        dictWrapper.eq(SysDict::getDictCode, dictType)
                    .eq(SysDict::getStatus, CommonConstants.ENABLE)
                    .eq(SysDict::getDelState, CommonConstants.NOT_DELETED);
        return success(sysDictService.list(dictWrapper));
    }



}
