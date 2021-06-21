package com.cloud.brave.mapper;

import com.cloud.brave.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 资源信息表 Mapper 接口
 * </p>
 *
 * @author yongchen
 * @since 2021-06-18
 */
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * @Author: yongchen
     * @Description: 根据角色获取访问权限
     * @Date: 17:33 2021/6/18
     * @Param: [roleId]
     * @return: java.util.List<com.cloud.brave.entity.SysMenu>
     **/
    List<SysMenu> findPermissionsByRoleId(Long roleId);
}
