package com.cloud.brave.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.brave.entity.SysDict;
import com.cloud.brave.service.SysDictService;
import com.cloud.core.constant.CommonConstants;
import com.cloud.core.mybatisplus.entity.BaseSuperEntuty;
import com.cloud.core.mybatisplus.page.PageParam;
import com.cloud.core.result.Result;
import com.cloud.log.annotation.BraveSysLog;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
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
     * @Description 分页获取字典列表
     * @Date 11:53 2021/7/26
     * @param pp
     * @return com.cloud.core.result.Result<com.baomidou.mybatisplus.core.metadata.IPage<com.cloud.brave.entity.SysDict>>
     **/
    @PostMapping("/page")
    @BraveSysLog(value = "分页获取字典列表")
    @ApiOperation(value = "分页获取字典列表", notes = "分页获取字典列表")
    public Result<IPage<SysDict>> page(@RequestBody @Validated(BaseSuperEntuty.OnlyQuery.class) PageParam<SysDict> pp) {
        LambdaQueryWrapper<SysDict> dictPageWapper = new LambdaQueryWrapper<>();
        dictPageWapper.eq(SysDict::getDelState, CommonConstants.NOT_DELETED)
                        .groupBy(SysDict::getDictCode)
                        .orderByAsc(SysDict::getDictSort);
        return success(sysDictService.page(pp.getPage(), dictPageWapper));
    }

    /**
     * @param dictType
     * @return com.cloud.core.result.Result<java.util.List < com.cloud.brave.entity.SysDict>>
     * @Author yongchen
     * @Description 根据字典类型获取字典
     * @Date 10:46 2021/7/13
     **/
    @GetMapping(value = "/type")
    @BraveSysLog(value = "根据字典类型获取字典")
    @ApiOperation(value = "根据字典类型获取字典", notes = "根据字典类型获取字典")
    public Result<List<SysDict>> findDictByType(@RequestParam String dictType) {
        LambdaQueryWrapper<SysDict> dictWrapper = new LambdaQueryWrapper<>();
        dictWrapper.eq(SysDict::getDictCode, dictType)
                .eq(SysDict::getDelState, CommonConstants.NOT_DELETED);
        return success(sysDictService.list(dictWrapper));
    }

    /**
     * @Author yongchen
     * @Description 新增字典
     * @Date 11:58 2021/7/26
     * @param sysDict
     * @return com.cloud.core.result.Result<java.lang.Boolean>
     **/
    @PostMapping("/save")
    @BraveSysLog(value = "新增字典")
    @ApiOperation(value = "新增字典", notes = "新增字典")
    public Result<Boolean> save(@RequestBody @Validated(BaseSuperEntuty.Save.class) SysDict sysDict){
        if (sysDictService.save(sysDict)) {
            return success(true);
        }
        return failed("新增字典失败");
    }

    /**
     * @Author yongchen
     * @Description 更新字典信息
     * @Date 13:43 2021/7/26
     * @param sysDict
     * @return com.cloud.core.result.Result<java.lang.Boolean>
     **/
    @PostMapping("/update")
    @BraveSysLog(value = "更新字典信息")
    @ApiOperation(value = "更新字典信息", notes = "更新字典信息")
    public Result<Boolean> update(@RequestBody @Validated(BaseSuperEntuty.Update.class) SysDict sysDict){
        if (sysDictService.updateById(sysDict)) {
            return success(true);
        }
        return failed("更新字典失败");
    }

    /**
     * @Author yongchen
     * @Description 根据字典id更改字典状态
     * @Date 13:48 2021/7/26
     * @param id
     * @param status
     * @return com.cloud.core.result.Result<java.lang.Boolean>
     **/
    @GetMapping("/updateDictStatusById")
    @BraveSysLog(value = "根据字典id更改字典状态")
    @ApiOperation(value = "根据字典id更改字典状态", notes = "根据字典id更改字典状态")
    public Result<Boolean> updateDictStatusById(@RequestParam Long id, @RequestParam String status){
        LambdaUpdateWrapper<SysDict> dictWapper = new LambdaUpdateWrapper<>();
        dictWapper.eq(SysDict::getId, id)
                    .set(SysDict::getStatus, status);
        if (sysDictService.update(dictWapper)) {
            return success(true);
        }
        return failed("更改字典状态失败");
    }

    /**
     * @Author yongchen
     * @Description 根据字典code更改字典状态
     * @Date 13:48 2021/7/26
     * @param code
     * @param status
     * @return com.cloud.core.result.Result<java.lang.Boolean>
     **/
    @GetMapping("/updateDictStatusByCode")
    @BraveSysLog(value = "根据字典code更改字典状态")
    @ApiOperation(value = "根据字典code更改字典状态", notes = "根据字典code更改字典状态")
    public Result<Boolean> updateDictStatusByCode(@RequestParam String code, @RequestParam String status){
        LambdaUpdateWrapper<SysDict> dictWapper = new LambdaUpdateWrapper<>();
        dictWapper.eq(SysDict::getDictCode, code)
                .set(SysDict::getStatus, status);
        if (sysDictService.update(dictWapper)) {
            return success(true);
        }
        return failed("更改字典状态失败");
    }

    /**
     * @Author yongchen
     * @Description 根据字典id删除字典
     * @Date 13:52 2021/7/26
     * @param id
     * @return com.cloud.core.result.Result<java.lang.Boolean>
     **/
    @GetMapping("/delById")
    @BraveSysLog(value = "根据字典id删除字典")
    @ApiOperation(value = "根据字典id删除字典", notes = "根据字典id删除字典")
    public Result<Boolean> delById(@RequestParam Long id){
        LambdaUpdateWrapper<SysDict> delWapper = new LambdaUpdateWrapper<>();
        delWapper.eq(SysDict::getId, id).set(SysDict::getDelState, CommonConstants.DELETED);
        if (sysDictService.update(delWapper)){
            return success(true);
        }
        return failed("删除字典失败");
    }

    /**
     * @Author yongchen
     * @Description 根据字典Code删除字典
     * @Date 13:52 2021/7/26
     * @param dictCode
     * @return com.cloud.core.result.Result<java.lang.Boolean>
     **/
    @GetMapping("/delByCode")
    @BraveSysLog(value = "根据字典Code删除字典")
    @ApiOperation(value = "根据字典Code删除字典", notes = "根据字典Code删除字典")
    public Result<Boolean> delByCode(@RequestParam String dictCode){
        LambdaUpdateWrapper<SysDict> delWapper = new LambdaUpdateWrapper<>();
        delWapper.eq(SysDict::getDictCode, dictCode).set(SysDict::getDelState, CommonConstants.DELETED);
        if (sysDictService.update(delWapper)){
            return success(true);
        }
        return failed("删除字典失败");
    }
}
