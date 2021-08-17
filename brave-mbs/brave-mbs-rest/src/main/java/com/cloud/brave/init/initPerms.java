package com.cloud.brave.init;

import com.cloud.brave.dto.RolePremsDTO;
import com.cloud.brave.service.SysRoleMenuService;
import com.cloud.brave.core.constant.AuthConstants;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author admin
 * @version 1.0
 * @description: 容器启动完成时加载角色权限规则至Redis缓存
 * @date 2021/7/27 15:25
 */
@Component
@AllArgsConstructor
public class initPerms implements CommandLineRunner {

    @Resource
    private SysRoleMenuService sysRoleMenuService;

    @Override
    public void run(String... args) {
        sysRoleMenuService.getRolePerms();
    }
}
