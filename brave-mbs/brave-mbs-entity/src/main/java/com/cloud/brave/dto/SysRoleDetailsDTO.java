package com.cloud.brave.dto;

import com.cloud.brave.entity.SysRole;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author admin
 * @version 1.0
 * @description: TODO
 * @date 2021/7/23 10:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysRoleDetailsDTO extends SysRole {
    private static final long serialVersionUID = -1541036665815773152L;

    /**
     * 角色权限
     **/
    @ApiModelProperty(value = "角色权限")
    private List<Long> perms;
}
