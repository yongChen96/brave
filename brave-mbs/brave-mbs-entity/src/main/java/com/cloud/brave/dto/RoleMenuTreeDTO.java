package com.cloud.brave.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author admin
 * @version 1.0
 * @description: TODO
 * @date 2021/7/14 15:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleMenuTreeDTO implements Serializable {
    private static final long serialVersionUID = -3077597486718005610L;

    List<Long> checkedKeys;
    List<MenuRouterDTO> menus;
}
