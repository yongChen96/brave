package com.cloud.brave.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author admin
 * @version 1.0
 * @description: 角色权限DTO
 * @date 2021/7/27 15:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePremsDTO implements Serializable {
    private static final long serialVersionUID = -4799158585869715304L;

    private Long id;
    private String urlPerm;
    private String btnPerm;
    private List<String> roles;
}
