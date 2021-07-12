package com.cloud.brave.dto;

import com.cloud.brave.entity.SysMenu;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author admin
 * @version 1.0
 * @description: 菜单目录DTO
 * @date 2021/7/9 11:07
 */
@Data
public class MenuDTO extends SysMenu {
    private static final long serialVersionUID = 4083264416098876469L;

    private List<MenuDTO> children;
}
