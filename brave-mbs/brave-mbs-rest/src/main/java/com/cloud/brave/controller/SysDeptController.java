package com.cloud.brave.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.brave.dto.DeptTreeDTO;
import com.cloud.brave.dto.SysDeptDTO;
import com.cloud.brave.entity.SysDept;
import com.cloud.brave.service.SysDeptService;
import com.cloud.brave.core.SnowflakeId.IdGenerate;
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
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.cloud.brave.core.base.controller.BaseController;

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
     * @param pp
     * @return com.cloud.core.result.Result<com.baomidou.mybatisplus.core.metadata.IPage < com.cloud.brave.entity.SysDept>>
     * @Author yongchen
     * @Description 分页查询部门信息
     * @Date 11:53 2021/7/21
     **/
    @PostMapping("/page")
    @BraveSysLog(value = "分页查询部门信息")
    @ApiOperation(value = "分页查询部门信息", notes = "分页查询部门信息")
    public Result<IPage<SysDept>> page(@RequestBody @Validated(BaseSuperEntuty.OnlyQuery.class) PageParam<SysDept> pp) {
        LambdaQueryWrapper<SysDept> pageWapper = new LambdaQueryWrapper<>();
        pageWapper.eq(SysDept::getDelState, CommonConstants.NOT_DELETED);
        SysDept dept = pp.getData();
        if (StringUtils.isNotBlank(dept.getDeptName())) {
            pageWapper.like(SysDept::getDeptName, dept.getDeptName());
        }
        if (StringUtils.isNotBlank(dept.getLeader())) {
            pageWapper.like(SysDept::getLeader, dept.getLeader());
        }
        if (StringUtils.isNotBlank(dept.getDeptStatus())) {
            pageWapper.eq(SysDept::getDeptStatus, dept.getDeptStatus());
        }
        Page<SysDept> page = sysDeptService.page(pp.getPage(), pageWapper);
        return success(page);
    }

    /**
     * @param
     * @return com.cloud.core.result.Result<java.util.List < com.cloud.brave.entity.SysDept>>
     * @Author yongchen
     * @Description 获取菜单列表
     * @Date 16:05 2021/6/24
     **/
    @PostMapping("/list")
    @BraveSysLog(value = "获取菜单列表")
    @ApiOperation(value = "获取菜单列表", notes = "获取菜单列表")
    public Result<List<SysDept>> list(@RequestBody SysDept sysDept) {
        LambdaQueryWrapper<SysDept> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysDept::getDelState, CommonConstants.NOT_DELETED)
                .eq(SysDept::getDeptStatus, CommonConstants.ENABLE)
                .orderByAsc(SysDept::getSort);
        return success(sysDeptService.list(queryWrapper));
    }

    /**
     * @param
     * @return com.cloud.core.result.Result<java.util.List < com.cloud.brave.dto.DeptTreeDTO>>
     * @Author yongchen
     * @Description 获取部门树
     * @Date 13:59 2021/7/13
     **/
    @GetMapping("/tree")
    @ApiOperation(value = "获取部门树", notes = "获取部门树")
    public Result<List<DeptTreeDTO>> treeselect(@RequestParam(value = "deptName", required = false) String deptName,
                                                @RequestParam(value = "leader", required = false) String leader,
                                                @RequestParam(value = "deptStatus", required = false) String deptStatus) {
        return success(sysDeptService.treeselect(deptName, leader, deptStatus));
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
            if (null == dept) {
                sysDept.setIdPath(sysDept.getParentId().toString());
                sysDept.setNamePath(sysDept.getDeptName());
            } else {
                sysDept.setIdPath(String.format("%s/%s", dept.getIdPath(), sysDept.getId()));
                sysDept.setNamePath(String.format("%s/%s", dept.getNamePath(), sysDept.getDeptName()));
            }
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
            if (null == dept) {
                sysDept.setIdPath(sysDept.getId().toString());
                sysDept.setNamePath(sysDept.getDeptName());
            } else {
                sysDept.setIdPath(String.format("%s/%s", dept.getIdPath(), sysDept.getId()));
                sysDept.setNamePath(String.format("%s/%s", dept.getNamePath(), sysDept.getDeptName()));
            }
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
    @GetMapping("/updateDeptStatus")
    @BraveSysLog(value = "禁用部门")
    @ApiOperation(value = "禁用部门", notes = "禁用部门")
    public Result<Boolean> disableDept(@RequestParam Long id, @RequestParam String deptStatus) {
        if (StringUtils.equals(CommonConstants.DISABLE, deptStatus)) {
            //判断下级部门是否正常使用，下级部门正常使用则上级部门不能禁用
            LambdaQueryWrapper<SysDept> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SysDept::getParentId, id)
                    .eq(SysDept::getDeptStatus, CommonConstants.ENABLE)
                    .eq(SysDept::getDelState, CommonConstants.NOT_DELETED);
            if (sysDeptService.count(queryWrapper) > 0) {
                throw new BraveException("该部门下存在使用中得部门，无法禁用");
            }
            LambdaUpdateWrapper<SysDept> wrapper = new LambdaUpdateWrapper<>();
            wrapper.eq(SysDept::getId, id)
                    .set(SysDept::getDeptStatus, CommonConstants.DISABLE);
            if (sysDeptService.update(wrapper)) {
                return success(true);
            }
        } else {
            //判断上级部门是否启用，上级部门未启用下级无法启用
            SysDept dept = sysDeptService.getById(id);
            LambdaQueryWrapper<SysDept> superDeptWapper = new LambdaQueryWrapper<>();
            superDeptWapper.eq(SysDept::getId, dept.getParentId())
                    .eq(SysDept::getDelState, CommonConstants.NOT_DELETED)
                    .eq(SysDept::getDeptStatus, CommonConstants.DISABLE);
            if (sysDeptService.count(superDeptWapper) > 0) {
                throw new BraveException("上级部门已被禁用，请先启用上级部门");
            }

            LambdaUpdateWrapper<SysDept> wrapper = new LambdaUpdateWrapper<>();
            wrapper.eq(SysDept::getId, id)
                    .set(SysDept::getDeptStatus, CommonConstants.ENABLE);
            if (sysDeptService.update(wrapper)) {
                return success(true);
            }
        }
        return failed("禁用部门信息");
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
        // 判断是否存在下级部门，存在下级部门则无法删除
        LambdaQueryWrapper<SysDept> deptWapper = new LambdaQueryWrapper<>();
        deptWapper.eq(SysDept::getParentId, id).eq(SysDept::getDelState, CommonConstants.NOT_DELETED);
        if (sysDeptService.count(deptWapper) > 0) {
            throw new BraveException("改部门下级存在部门，无法删除");
        }
        LambdaUpdateWrapper<SysDept> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(SysDept::getId, id)
                .set(SysDept::getDelState, CommonConstants.DELETED);
        if (sysDeptService.update(updateWrapper)) {
            return success(true);
        }
        return failed("删除部门信息失败");
    }
}
