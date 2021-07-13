package com.cloud.brave.service;

import com.cloud.brave.dto.DeptTreeDTO;
import com.cloud.brave.entity.SysDept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 部门信息表 服务类
 * </p>
 *
 * @author yongchen
 * @since 2021-06-24
 */
public interface SysDeptService extends IService<SysDept> {
    /**
     * @Author yongchen
     * @Description 获取部门树
     * @Date 11:54 2021/7/13
     * @param
     * @return java.util.List<com.cloud.brave.dto.DeptTreeDTO>
     **/
    List<DeptTreeDTO> treeselect();
}
