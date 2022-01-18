package com.cloud.brave.init;

import com.cloud.brave.service.SysRoleMenuService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * @author admin
 * @version 1.0
 * @description: 容器启动完成时加载角色权限规则至Redis缓存
 * @date 2021/7/27 15:25
 */
@Component
@AllArgsConstructor
public class RoleCache implements CommandLineRunner {

    @Resource
    private SysRoleMenuService sysRoleMenuService;

    @Override
    public void run(String... args) {
        sysRoleMenuService.getRolePerms();
    }
}
