package com.cloud.brave.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cloud.brave.dto.DeptTreeDTO;
import com.cloud.brave.entity.SysDept;
import com.cloud.brave.mapper.SysDeptMapper;
import com.cloud.brave.service.SysDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.brave.core.constant.CommonConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 部门信息表 服务实现类
 * </p>
 *
 * @author yongchen
 * @since 2021-06-24
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    /**
     * @param
     * @return java.util.List<com.cloud.brave.dto.DeptTreeDTO>
     * @Author yongchen
     * @Description 获取部门树
     * @Date 11:39 2021/7/13
     **/
    @Override
    public List<DeptTreeDTO> treeselect(String deptName, String leader, String deptStatus) {
        LambdaQueryWrapper<SysDept> deptWapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(deptName)){
            deptWapper.eq(SysDept::getDeptName, deptName);
        }
        if (StringUtils.isNotBlank(leader)){
            deptWapper.eq(SysDept::getLeader, leader);
        }
        if (StringUtils.isNotBlank(deptStatus)){
            deptWapper.eq(SysDept::getDeptStatus, deptStatus);
        }
        deptWapper.eq(SysDept::getDelState, CommonConstants.NOT_DELETED);
        List<SysDept> depts = this.list(deptWapper);
        return getTree(depts);
    }

    /**
     * @Author yongchen
     * @Description 构建部门树
     * @Date 11:58 2021/7/13
     * @param depts
     * @return java.util.List<com.cloud.brave.dto.DeptTreeDTO>
     **/
    private List<DeptTreeDTO> getTree(List<SysDept> depts) {
        List<DeptTreeDTO> list = new ArrayList<>();
        for (SysDept dept : depts) {
            DeptTreeDTO treeDTO = new DeptTreeDTO();
            BeanUtils.copyProperties(dept, treeDTO);
            if (CommonConstants.TOP_DEPT == dept.getParentId()) {
                recursionDept(treeDTO, depts);
                list.add(treeDTO);
            }
        }
        return list;
    }

    /**
     * @Author yongchen
     * @Description 递归逻辑
     * @Date 11:58 2021/7/13
     * @param dept
     * @param depts
     * @return void
     **/
    private void recursionDept(DeptTreeDTO dept, List<SysDept> depts) {
        // 得到子节点列表
        List<DeptTreeDTO> childList = getChildList(dept, depts);
        dept.setChildren(childList);
        for (DeptTreeDTO treeDTO : childList) {
            if (!CollectionUtils.isEmpty(getChildList(treeDTO, depts))) {
                recursionDept(treeDTO, depts);
            }
        }
    }

    /**
     * @param dept
     * @param depts
     * @return java.util.List<com.cloud.brave.entity.SysDept>
     * @Author yongchen
     * @Description 获取所有子节点
     * @Date 11:48 2021/7/13
     **/
    private List<DeptTreeDTO> getChildList(DeptTreeDTO dept, List<SysDept> depts) {
        List<DeptTreeDTO> childList = new ArrayList<>();
        for (SysDept sysDept : depts) {
            if (dept.getId().longValue() == sysDept.getParentId().longValue()) {
                DeptTreeDTO treeDTO = new DeptTreeDTO();
                BeanUtils.copyProperties(sysDept, treeDTO);
                childList.add(treeDTO);
            }
        }
        return childList;
    }
}
