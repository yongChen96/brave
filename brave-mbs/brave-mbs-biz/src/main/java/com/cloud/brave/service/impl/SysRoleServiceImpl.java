package com.cloud.brave.service.impl;

import com.cloud.brave.entity.SysRole;
import com.cloud.brave.mapper.SysRoleMapper;
import com.cloud.brave.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author yongchen
 * @since 2021-06-17
 */
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    private final SysRoleMapper sysRoleMapper;

    /**
     * @Author: yongchen
     * @Description: 根据用户获取用户角色
     * @Date: 17:13 2021/6/18
     * @Param: [userId]
     * @return: java.util.List<com.cloud.brave.entity.SysRole>
     **/
    @Override
    public List<SysRole> findRolesByUserId(Long userId) {
        return sysRoleMapper.findRolesByUserId(userId);
    }
}
