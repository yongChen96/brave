package com.cloud.brave.dto;

import com.cloud.brave.entity.SysDept;
import com.cloud.brave.entity.SysUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: UserInfoDTO
 * @Description: 用户信息DTO
 * @Author: yongchen
 * @Date: 2021/6/4 9:29
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO  implements Serializable {
    private static final long serialVersionUID = 909348299019328891L;
    /**
     * 用户基本信息
     */
    private SysUser sysUser;
    /**
     * 用户所属部门信息
     */
    private SysDept sysDept;
    /**
     * 权限标识集合
     */
    private String[] permissions;
    /**
     * 角色集合
     */
    private Long[] roles;
}
