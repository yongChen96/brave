package com.cloud.brave.service.impl;

import com.cloud.brave.entity.SysRole;
import com.cloud.brave.entity.SysUserRole;
import com.cloud.brave.mapper.SysRoleMapper;
import com.cloud.brave.mapper.SysUserRoleMapper;
import com.cloud.brave.service.SysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户角色中间表 服务实现类
 * </p>
 *
 * @author yongchen
 * @since 2021-06-07
 */
@Service
@RequiredArgsConstructor
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {


}
