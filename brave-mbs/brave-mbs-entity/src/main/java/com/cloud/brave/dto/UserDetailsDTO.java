package com.cloud.brave.dto;

import com.cloud.brave.entity.SysDept;
import com.cloud.brave.entity.SysRole;
import com.cloud.brave.entity.SysUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author admin
 * @version 1.0
 * @description: 用户详细信息
 * @date 2021/7/15 15:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDTO extends SysUser {
    private static final long serialVersionUID = -5102823123008209629L;

    /**
     * 部门信息
     **/
    private SysDept sysDept;
    /**
     * 角色信息
     **/
    private  List<Long> roles;
}
