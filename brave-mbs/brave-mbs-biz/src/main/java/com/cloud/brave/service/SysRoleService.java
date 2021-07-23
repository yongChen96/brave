package com.cloud.brave.service;

import com.cloud.brave.dto.SysRoleDetailsDTO;
import com.cloud.brave.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author yongchen
 * @since 2021-06-17
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * @Author: yongchen
     * @Description: 根据用户获取用户角色
     * @Date: 17:13 2021/6/18
     * @Param: [userId]
     * @return: java.util.List<com.cloud.brave.entity.SysRole>
     **/
    List<SysRole> findRolesByUserId(Long userId);

    /**
     * @Author yongchen
     * @Description 获取角色详细信息
     * @Date 10:14 2021/7/23
     * @param id
     * @return com.cloud.brave.dto.SysRoleDetailsDTO
     **/
    SysRoleDetailsDTO findRoleDetail(Long id);

    /**
     * @Author yongchen
     * @Description 新增角色信息
     * @Date 10:50 2021/7/23
     * @param dto
     * @return java.lang.Boolean
     **/
    Boolean saveRole(SysRoleDetailsDTO dto);

    /**
     * @Author yongchen
     * @Description 更新角色信息
     * @Date 10:58 2021/7/23
     * @param dto
     * @return java.lang.Boolean
     **/
    Boolean updateRole(SysRoleDetailsDTO dto);
}
