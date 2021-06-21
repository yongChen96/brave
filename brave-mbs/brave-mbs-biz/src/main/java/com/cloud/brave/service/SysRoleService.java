package com.cloud.brave.service;

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
}
