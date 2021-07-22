package com.cloud.brave.service.impl;

import cn.hutool.core.collection.ListUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cloud.brave.dto.MenuDTO;
import com.cloud.brave.entity.SysMenu;
import com.cloud.brave.mapper.SysMenuMapper;
import com.cloud.brave.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.core.constant.CommonConstants;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 资源信息表 服务实现类
 * </p>
 *
 * @author yongchen
 * @since 2021-06-18
 */
@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    private final SysMenuMapper sysMenuMapper;
    private final RedisTemplate redisTemplate;

    /**
     * @param userId
     * @return java.util.List<com.cloud.brave.dto.MenuDTO>
     * @Author yongchen
     * @Description 获取当前用户菜单路由信息
     * @Date 10:46 2021/7/9
     **/
    @Override
    public List<MenuDTO> findMenusByUserId(Long userId) {
        List<MenuDTO> menuTree = new ArrayList<>();
        List<MenuDTO> userMenus = sysMenuMapper.findMenusByUserId(userId);
        for (MenuDTO userMenu : userMenus) {
            if (CommonConstants.TOP_MENU == userMenu.getParentId()) {
                //递归获取子节点
                recursionChildren(userMenu, userMenus);
                menuTree.add(userMenu);
            }
        }
        return menuTree;
    }

    /**
     * @param
     * @return java.util.List<com.cloud.brave.entity.SysMenu>
     * @Author yongchen
     * @Description 获取资源权限信息
     * @Date 15:14 2021/6/25
     **/
    @Override
    public List<SysMenu> findMenu() {
        List<SysMenu> sysMenus = sysMenuMapper.findPermissionsRole();
        return null;
    }

    /**
     * @Author: yongchen
     * @Description: 根据角色获取访问权限
     * @Date: 17:32 2021/6/18
     * @Param: [roleId]
     * @return: java.util.List<com.cloud.brave.entity.SysMenu>
     **/
    @Override
    public List<SysMenu> findPermissionsByRoleId(Long roleId) {
        return sysMenuMapper.findPermissionsByRoleId(roleId);
    }

    /**
     * @Author yongchen
     * @Description 获取角色树
     * @Date 17:14 2021/7/22
     * @param menuName 资源名称
     * @param isExternalLink 是否外链
     * @param menuType 资源类型
     * @param isDisplay 显示状态
     * @param status 资源状态
     * @return java.util.List<com.cloud.brave.dto.MenuDTO>
     **/
    @Override
    public List<MenuDTO> getRoleTree(String menuName, String isExternalLink, String menuType, String isDisplay, String status) {
        List<MenuDTO> menuTree = new ArrayList<>();
        LambdaQueryWrapper<SysMenu> menuTreeWapper = new LambdaQueryWrapper<>();
        menuTreeWapper.eq(SysMenu::getDelState, CommonConstants.NOT_DELETED);
        if (StringUtils.isNotBlank(menuName)) {
            menuTreeWapper.eq(SysMenu::getMenuName, menuName);
        }
        if (StringUtils.isNotBlank(isExternalLink)) {
            menuTreeWapper.eq(SysMenu::getIsExternalLink, isExternalLink);
        }
        if (StringUtils.isNotBlank(menuType)) {
            menuTreeWapper.eq(SysMenu::getMenuType, menuType);
        }
        if (StringUtils.isNotBlank(isDisplay)) {
            menuTreeWapper.eq(SysMenu::getIsDisplay, isDisplay);
        }
        if (StringUtils.isNotBlank(status)) {
            menuTreeWapper.eq(SysMenu::getStatus, status);
        }
        List<SysMenu> sysMenus = sysMenuMapper.selectList(menuTreeWapper);
        if (!CollectionUtils.isEmpty(sysMenus)){
            List<MenuDTO> list = new ArrayList<>();
            sysMenus.forEach(sysMenu -> {
                MenuDTO menuDTO = new MenuDTO();
                BeanUtils.copyProperties(sysMenu, menuDTO);
                list.add(menuDTO);
            });
            if (!CollectionUtils.isEmpty(list)){
                for (MenuDTO menuDTO : list) {
                    if (CommonConstants.TOP_MENU == menuDTO.getParentId()) {
                        //递归获取子节点
                        recursionChildren(menuDTO, list);
                        menuTree.add(menuDTO);
                    }
                }
            }
        }
        return menuTree;
    }

    /**
     * @param userMenu
     * @param userMenus
     * @return void
     * @Author yongchen
     * @Description 递归获取子节点
     * @Date 13:52 2021/7/22
     **/
    private void recursionChildren(MenuDTO userMenu, List<MenuDTO> userMenus) {
        List<MenuDTO> childrens = new ArrayList<>();
        for (MenuDTO menu : userMenus) {
            if (menu.getParentId().longValue() == userMenu.getId().longValue()) {
                recursionChildren(menu, userMenus);
                childrens.add(menu);
            }
        }
        userMenu.setChildren(childrens);
    }
}
