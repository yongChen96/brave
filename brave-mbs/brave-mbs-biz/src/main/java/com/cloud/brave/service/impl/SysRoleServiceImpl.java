package com.cloud.brave.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cloud.brave.dto.SysRoleDetailsDTO;
import com.cloud.brave.entity.SysRole;
import com.cloud.brave.entity.SysRoleMenu;
import com.cloud.brave.mapper.SysRoleMapper;
import com.cloud.brave.service.SysRoleMenuService;
import com.cloud.brave.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.brave.core.SnowflakeId.IdGenerate;
import com.cloud.brave.core.constant.CommonConstants;
import com.cloud.brave.core.exception.BraveException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private final SysRoleMenuService sysRoleMenuService;
    private final IdGenerate<Long> idGenerate;

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

    /**
     * @param id
     * @return com.cloud.brave.dto.SysRoleDetailsDTO
     * @Author yongchen
     * @Description 获取角色详细信息
     * @Date 10:14 2021/7/23
     **/
    @Override
    public SysRoleDetailsDTO findRoleDetail(Long id) {
        SysRole role = getById(id);
        if (null == role) {
            throw new BraveException("角色不存在");
        }
        SysRoleDetailsDTO sysRoleDetailsDTO = BeanUtil.copyProperties(role, SysRoleDetailsDTO.class);
        // 获取角色权限
        LambdaQueryWrapper<SysRoleMenu> roleMenuWapper = new LambdaQueryWrapper<>();
        roleMenuWapper.eq(SysRoleMenu::getRoleId, id);
        List<SysRoleMenu> list = sysRoleMenuService.list(roleMenuWapper);
        sysRoleDetailsDTO.setPerms(list.stream().map(item -> item.getMenuId()).collect(Collectors.toList()));
        return sysRoleDetailsDTO;
    }

    /**
     * @param dto
     * @return java.lang.Boolean
     * @Author yongchen
     * @Description 新增角色信息
     * @Date 10:50 2021/7/23
     **/
    @Override
    public Boolean saveRole(SysRoleDetailsDTO dto) {
        //获取角色id
        Long roleId = idGenerate.idGenerate();
        //角色名称、角色编码判重
        LambdaQueryWrapper<SysRole> roleNameWapper = new LambdaQueryWrapper<>();
        roleNameWapper.eq(SysRole::getRoleName, dto.getRoleName()).eq(SysRole::getDelState, CommonConstants.NOT_DELETED);
        if (this.count(roleNameWapper) > 0) {
            throw new BraveException("该角色名已存在");
        }
        LambdaQueryWrapper<SysRole> roleCodeWapper = new LambdaQueryWrapper<>();
        roleNameWapper.eq(SysRole::getRoleCode, dto.getRoleCode()).eq(SysRole::getDelState, CommonConstants.NOT_DELETED);
        if (this.count(roleCodeWapper) > 0) {
            throw new BraveException("角色编码已存在");
        }
        // 保存角色信息
        dto.setId(roleId);
        if (this.save(dto)) {
            // 保存角色权限信息
            List<Long> perms = dto.getPerms();
            if (!CollectionUtils.isEmpty(perms)) {
                List<SysRoleMenu> rolePerms = new ArrayList<>();
                perms.forEach(item -> {
                    SysRoleMenu sysRoleMenu = new SysRoleMenu();
                    sysRoleMenu.setRoleId(roleId);
                    sysRoleMenu.setMenuId(item);
                    rolePerms.add(sysRoleMenu);
                });
                return sysRoleMenuService.saveBatch(rolePerms);
            }
            return true;
        }
        return false;
    }

    /**
     * @param dto
     * @return java.lang.Boolean
     * @Author yongchen
     * @Description 更新角色信息
     * @Date 10:58 2021/7/23
     **/
    @Override
    public Boolean updateRole(SysRoleDetailsDTO dto) {
        //角色名称、角色编码判重
        LambdaQueryWrapper<SysRole> roleNameWapper = new LambdaQueryWrapper<>();
        roleNameWapper.eq(SysRole::getRoleName, dto.getRoleName())
                .ne(SysRole::getId, dto.getId())
                .eq(SysRole::getDelState, CommonConstants.NOT_DELETED);
        if (this.count(roleNameWapper) > 0) {
            throw new BraveException("该角色名已存在");
        }
        LambdaQueryWrapper<SysRole> roleCodeWapper = new LambdaQueryWrapper<>();
        roleCodeWapper.eq(SysRole::getRoleCode, dto.getRoleCode())
                .ne(SysRole::getId, dto.getId())
                .eq(SysRole::getDelState, CommonConstants.NOT_DELETED);
        if (this.count(roleCodeWapper) > 0) {
            throw new BraveException("角色编码已存在");
        }

        //更新角色信息
        if (this.updateById(dto)) {
            // 更新角色权限信息
            List<Long> perms = dto.getPerms();
            if (!CollectionUtils.isEmpty(perms)) {
                // 物理删除角色所有权限
                if (sysRoleMenuService.removeRoleMenu(dto.getId())) {
                    //添加角色新权限信息
                    List<SysRoleMenu> rolePerms = new ArrayList<>();
                    perms.forEach(item -> {
                        SysRoleMenu sysRoleMenu = new SysRoleMenu();
                        sysRoleMenu.setRoleId(dto.getId());
                        sysRoleMenu.setMenuId(item);
                        rolePerms.add(sysRoleMenu);
                    });
                    return sysRoleMenuService.saveBatch(rolePerms);
                }
                throw new BraveException("更新角色权限信息失败");
            }
            return true;
        }
        return false;
    }
}
