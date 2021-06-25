package com.cloud.brave.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cloud.brave.dto.SysDeptDTO;
import com.cloud.brave.entity.SysDept;
import com.cloud.brave.service.SysDeptService;
import com.cloud.core.SnowflakeId.IdGenerate;
import com.cloud.core.constant.CommonConstants;
import com.cloud.core.exception.BraveException;
import com.cloud.core.result.Result;
import com.cloud.log.annotation.BraveSysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.cloud.core.base.controller.BaseController;

import java.util.List;

/**
 * <p>
 * 部门信息表 前端控制器
 * </p>
 *
 * @author yongchen
 * @since 2021-06-24
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/sysDept")
@Api(value = "SysDeptController", tags = "部门信息表")
public class SysDeptController extends BaseController {

    private final SysDeptService sysDeptService;
    private final IdGenerate<Long> idGenerate;

    /**
     * @param
     * @return com.cloud.core.result.Result<java.util.List < com.cloud.brave.entity.SysDept>>
     * @Author yongchen
     * @Description 获取菜单列表
     * @Date 16:05 2021/6/24
     **/
    @GetMapping("/list")
    @BraveSysLog(value = "获取菜单列表")
    @ApiOperation(value = "获取菜单列表", notes = "获取菜单列表")
    public Result<List<SysDept>> list() {
        LambdaQueryWrapper<SysDept> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysDept::getDelState, CommonConstants.NOT_DELETED)
                .eq(SysDept::getParentId, CommonConstants.TOP_MENU)
                .orderByAsc(SysDept::getSort);
        return success(sysDeptService.list(queryWrapper));
    }

    /**
     * @param id 部门id
     * @return com.cloud.core.result.Result<com.cloud.brave.entity.SysDept>
     * @Author yongchen
     * @Description 查询部门详细信息
     * @Date 14:27 2021/6/24
     **/
    @GetMapping("/get")
    @BraveSysLog(value = "查询部门详细信息")
    @ApiOperation(value = "查询部门详细信息", notes = "查询部门详细信息")
    public Result<SysDept> get(@RequestParam Long id) {
        return success(sysDeptService.getById(id));
    }

    /**
     * @param id 部门id
     * @return com.cloud.core.result.Result<java.util.List < com.cloud.brave.entity.SysDept>>
     * @Author yongchen
     * @Description 根据部门id获取下级部门信息
     * @Date 15:48 2021/6/24
     **/
    @GetMapping("/getSubordinateDept")
    @BraveSysLog(value = "根据部门id获取下级部门信息")
    @ApiOperation(value = "根据部门id获取下级部门信息", notes = "根据部门id获取下级部门信息")
    public Result<List<SysDept>> getSubordinateDept(@RequestParam Long id) {
        LambdaQueryWrapper<SysDept> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysDept::getParentId, id)
                .eq(SysDept::getDeptStatus, CommonConstants.ENABLE)
                .eq(SysDept::getDelState, CommonConstants.NOT_DELETED);
        return success(sysDeptService.list(queryWrapper));
    }

    /**
     * @param sysDeptDTO 部门实体
     * @return com.cloud.core.result.Result<java.lang.Boolean>
     * @Author yongchen
     * @Description 新增部门
     * @Date 14:57 2021/6/24
     **/
    @PostMapping("/save")
    @BraveSysLog(value = "新增部门")
    @ApiOperation(value = "新增部门", notes = "新增部门")
    public Result<Boolean> save(@RequestBody @Validated SysDeptDTO sysDeptDTO) {
        SysDept sysDept = new SysDept();
        Long deptId = idGenerate.idGenerate();
        BeanUtils.copyProperties(sysDeptDTO, sysDept);
        sysDept.setId(deptId);
        if (sysDept.getParentId() != null) {
            SysDept dept = sysDeptService.getById(sysDeptDTO.getParentId());
            sysDept.setIdPath(String.format("%s/%s", dept.getIdPath(), sysDept.getId()));
            sysDept.setNamePath(String.format("%s/%s", dept.getNamePath(), sysDept.getDeptName()));
        } else {
            sysDept.setParentId(CommonConstants.TOP_MENU);
            sysDept.setIdPath(sysDept.getId().toString());
            sysDept.setNamePath(sysDept.getDeptName());
        }
        sysDept.setDelState(CommonConstants.NOT_DELETED);
        if (sysDeptService.save(sysDept)) {
            return success(true);
        }
        return failed("新增部门失败");
    }

    /**
     * @param sysDeptDTO 部门实体
     * @return com.cloud.core.result.Result<java.lang.Boolean>
     * @Author yongchen
     * @Description 更新部门信息
     * @Date 15:03 2021/6/24
     **/
    @PostMapping("/update")
    @BraveSysLog(value = "更新部门信息")
    @ApiOperation(value = "更新部门信息", notes = "更新部门信息")
    public Result<Boolean> update(@RequestBody @Validated SysDeptDTO sysDeptDTO) {
        SysDept sysDept = new SysDept();
        BeanUtils.copyProperties(sysDeptDTO, sysDept);
        if (sysDept.getParentId() != null) {
            SysDept dept = sysDeptService.getById(sysDeptDTO.getParentId());
            sysDept.setIdPath(String.format("%s/%s", dept.getIdPath(), sysDept.getId()));
            sysDept.setNamePath(String.format("%s/%s", dept.getNamePath(), sysDept.getDeptName()));
        } else {
            sysDept.setIdPath(sysDept.getId().toString());
            sysDept.setNamePath(sysDept.getDeptName());
        }
        if (sysDeptService.updateById(sysDept)) {
            return success(true);
        }
        return failed("更新部门信息失败");
    }

    /**
     * @param id 部门id
     * @return com.cloud.core.result.Result<java.lang.Boolean>
     * @Author yongchen
     * @Description 禁用部门
     * @Date 15:09 2021/6/24
     **/
    @GetMapping("/disableDept")
    @BraveSysLog(value = "禁用部门")
    @ApiOperation(value = "禁用部门", notes = "禁用部门")
    public Result<Boolean> disableDept(@RequestParam Long id) {
        LambdaQueryWrapper<SysDept> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.likeLeft(SysDept::getIdPath, id)
                .eq(SysDept::getDeptStatus, CommonConstants.ENABLE)
                .eq(SysDept::getDelState, CommonConstants.NOT_DELETED);
        if (sysDeptService.count(queryWrapper) > 0) {
            throw new BraveException("该部门下有未禁用部门");
        }
        LambdaUpdateWrapper<SysDept> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(SysDept::getId, id)
                .set(SysDept::getDeptStatus, CommonConstants.DISABLE);
        if (sysDeptService.update(wrapper)) {
            return success(true);
        }
        return failed("禁用部门信息");
    }

    /**
     * @param id 部门id
     * @return com.cloud.core.result.Result<java.lang.Boolean>
     * @Author yongchen
     * @Description 启用部门
     * @Date 15:26 2021/6/24
     **/
    @GetMapping("/enableDept")
    @BraveSysLog(value = "启用部门")
    @ApiOperation(value = "启用部门", notes = "启用部门")
    public Result<Boolean> enableDept(@RequestParam Long id) {
        SysDept dept = sysDeptService.getById(id);
        String[] split = dept.getIdPath().split("/");
        for (String deptId : split) {
            LambdaQueryWrapper<SysDept> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SysDept::getId, deptId)
                    .eq(SysDept::getDeptStatus, CommonConstants.DISABLE)
                    .eq(SysDept::getDelState, CommonConstants.NOT_DELETED);
            if (sysDeptService.count(queryWrapper) > 0) {
                throw new BraveException("该部门上级被禁用");
            }
        }
        LambdaUpdateWrapper<SysDept> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(SysDept::getId, id)
                .set(SysDept::getDeptStatus, CommonConstants.ENABLE);
        if (sysDeptService.update(wrapper)) {
            return success(true);
        }
        return failed("启用部门失败");
    }

    /**
     * @param id 部门id
     * @return com.cloud.core.result.Result<java.lang.Boolean>
     * @Author yongchen
     * @Description 删除部门信息
     * @Date 15:29 2021/6/24
     **/
    @GetMapping("delete")
    @BraveSysLog(value = "删除部门信息")
    @ApiOperation(value = "删除部门信息", notes = "删除部门信息")
    public Result<Boolean> delete(@RequestParam Long id) {
        LambdaUpdateWrapper<SysDept> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(SysDept::getId, id)
                .set(SysDept::getDelState, CommonConstants.DELETED);
        if (sysDeptService.update(updateWrapper)) {
            return success(true);
        }
        return failed("删除部门信息失败");
    }
}