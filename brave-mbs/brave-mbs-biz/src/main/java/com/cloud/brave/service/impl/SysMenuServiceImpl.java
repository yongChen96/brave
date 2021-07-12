package com.cloud.brave.service.impl;

import com.cloud.brave.dto.MenuDTO;
import com.cloud.brave.dto.MenuRouterDTO;
import com.cloud.brave.dto.MetaDTO;
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
import java.util.LinkedList;
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
        List<MenuDTO> userMenus = sysMenuMapper.findMenusByUserId(userId);
        return getMenuTree(userMenus);
    }

    /**
     * @param menus
     * @return java.util.List<com.cloud.brave.dto.MenuRouterDTO>
     * @Author yongchen
     * @Description 构建菜单结构
     * @Date 15:23 2021/7/9
     **/
    @Override
    public List<MenuRouterDTO> buildMenus(List<MenuDTO> menus) {
        List<MenuRouterDTO> routers = new LinkedList<MenuRouterDTO>();
        for (MenuDTO menu : menus) {
            MenuRouterDTO router = new MenuRouterDTO();
            router.setHidden(!StringUtils.equals(CommonConstants.ENABLE, menu.getStatus()));
            router.setName(getRouteName(menu));
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));
            router.setMeta(new MetaDTO(menu.getMenuName(), menu.getIcon(), !StringUtils.equals(CommonConstants.ENABLE, menu.getIsCache()), ishttp(menu.getPath()) ? menu.getPath() : null));
            List<MenuDTO> cMenus = menu.getChildren();
            if (!CollectionUtils.isEmpty(cMenus)) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            } else if (isMenuFrame(menu)) {
                router.setMeta(null);
                List<MenuRouterDTO> childrenList = new ArrayList<MenuRouterDTO>();
                MenuRouterDTO children = new MenuRouterDTO();
                children.setPath(menu.getPath());
                children.setComponent(menu.getComponent());
                children.setName(StringUtils.capitalize(menu.getPath()));
                children.setMeta(new MetaDTO(menu.getMenuName(), menu.getIcon(), !StringUtils.equals(CommonConstants.ENABLE, menu.getIsCache()), ishttp(menu.getPath()) ? menu.getPath() : null));
                childrenList.add(children);
                router.setChildren(childrenList);
            } else if (menu.getParentId().intValue() == 0 && isInnerLink(menu)) {
                router.setMeta(null);
                router.setPath("/inner");
                List<MenuRouterDTO> childrenList = new ArrayList<MenuRouterDTO>();
                MenuRouterDTO children = new MenuRouterDTO();
                String routerPath = StringUtils.replaceEach(menu.getPath(), new String[]{CommonConstants.HTTP, CommonConstants.HTTPS}, new String[]{"", ""});
                children.setPath(routerPath);
                children.setComponent(CommonConstants.INNER_LINK);
                children.setName(StringUtils.capitalize(routerPath));
                children.setMeta(new MetaDTO(menu.getMenuName(), menu.getIcon(), menu.getPath()));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
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
     * @param list
     * @return java.util.List<com.cloud.brave.dto.MenuDTO>
     * @Author yongchen
     * @Description 获取菜单树结构
     * @Date 14:16 2021/7/9
     **/
    private List<MenuDTO> getMenuTree(List<MenuDTO> list) {
        List<MenuDTO> menus = new ArrayList<>();
        for (MenuDTO menu : list) {
            if (CommonConstants.TOP_MENU == menu.getParentId()) {
                recursionMenu(menu, list);
                menus.add(menu);
            }
        }
        return menus;
    }

    /**
     * @param menuDTO
     * @param list
     * @return void
     * @Author yongchen
     * @Description 递归列表
     * @Date 14:16 2021/7/9
     **/
    private void recursionMenu(MenuDTO menuDTO, List<MenuDTO> list) {
        // 得到子节点列表
        List<MenuDTO> childList = getChildList(menuDTO, list);
        menuDTO.setChildren(childList);
        for (MenuDTO tChild : childList) {
            if (hasChild(tChild, list)) {
                recursionMenu(tChild, list);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<MenuDTO> getChildList(MenuDTO menuDTO, List<MenuDTO> list) {
        List<MenuDTO> tlist = new ArrayList<>();
        for (MenuDTO menu : list) {
            if (menuDTO.getId() == menu.getParentId()) {
                tlist.add(menu);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(MenuDTO t, List<MenuDTO> list) {
        return getChildList(t, list).size() > 0 ? true : false;
    }

    /**
     * 获取路由名称
     *
     * @param menu 菜单信息
     * @return 路由名称
     */
    public String getRouteName(MenuDTO menu) {
        String routerName = StringUtils.capitalize(menu.getPath());
        // 非外链并且是一级目录（类型为目录）
        if (isMenuFrame(menu)) {
            routerName = StringUtils.EMPTY;
        }
        return routerName;
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(MenuDTO menu) {
        String routerPath = menu.getPath();
        // 内链打开外网方式
        if (menu.getParentId().intValue() != 0 && isInnerLink(menu)) {
            routerPath = StringUtils.replaceEach(routerPath, new String[]{CommonConstants.HTTP, CommonConstants.HTTPS}, new String[]{"", ""});
        }
        // 非外链并且是一级目录（类型为目录）
        if (0 == menu.getParentId().intValue() && CommonConstants.TYPE_DIR.equals(menu.getMenuType())
                && CommonConstants.NO_FRAME.equals(menu.getIsFrame())) {
            routerPath = "/" + menu.getPath();
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMenuFrame(menu)) {
            routerPath = "/";
        }
        return routerPath;
    }

    /**
     * 获取组件信息
     *
     * @param menu 菜单信息
     * @return 组件信息
     */
    public String getComponent(MenuDTO menu) {
        String component = CommonConstants.LAYOUT;
        if (StringUtils.isNotEmpty(menu.getComponent()) && !isMenuFrame(menu)) {
            component = menu.getComponent();
        } else if (StringUtils.isEmpty(menu.getComponent()) && menu.getParentId().intValue() != 0 && isInnerLink(menu)) {
            component = CommonConstants.INNER_LINK;
        } else if (StringUtils.isEmpty(menu.getComponent()) && isParentView(menu)) {
            component = CommonConstants.PARENT_VIEW;
        }
        return component;
    }

    /**
     * 是否为菜单内部跳转
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isMenuFrame(MenuDTO menu) {
        return menu.getParentId().intValue() == 0 && CommonConstants.TYPE_MENU.equals(menu.getMenuType())
                && menu.getIsFrame().equals(CommonConstants.NO_FRAME);
    }

    /**
     * 是否为内链组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isInnerLink(MenuDTO menu) {
        return menu.getIsFrame().equals(CommonConstants.NO_FRAME) && ishttp(menu.getPath());
    }

    /**
     * 是否为parent_view组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isParentView(MenuDTO menu) {
        return menu.getParentId().intValue() != 0 && CommonConstants.TYPE_DIR.equals(menu.getMenuType());
    }

    /**
     * 是否为http(s)://开头
     *
     * @param link 链接
     * @return 结果
     */
    public static boolean ishttp(String link) {
        return StringUtils.startsWithAny(link, CommonConstants.HTTP, CommonConstants.HTTPS);
    }

}
