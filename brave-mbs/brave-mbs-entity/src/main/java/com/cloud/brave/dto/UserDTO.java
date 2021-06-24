package com.cloud.brave.dto;

import com.cloud.brave.entity.SysUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: UserDTO
 * @Description: 用户信息
 * @Author: yongchen
 * @Date: 2021/6/7 11:05
 **/
@Data
public class UserDTO extends SysUser {
    private static final long serialVersionUID = -7661583447839940675L;

    /**
     * 角色信息
     **/
    @NotNull(message = "用户角色不能为空")
    @ApiModelProperty(value = "用户角色")
    private List<Long> roles;

}
