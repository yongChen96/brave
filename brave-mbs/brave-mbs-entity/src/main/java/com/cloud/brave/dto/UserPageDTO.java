package com.cloud.brave.dto;

import com.cloud.brave.entity.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author admin
 * @version 1.0
 * @description: 用户PageDTO
 * @date 2021/7/13 17:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "UserPageDTO")
public class UserPageDTO extends SysUser {
    private static final long serialVersionUID = -7282320476414826512L;

    /**
     * 部门名称
     **/
    @ApiModelProperty(value = "部门名称")
    private String deptName;
}
