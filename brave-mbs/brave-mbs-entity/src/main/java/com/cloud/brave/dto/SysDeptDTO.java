package com.cloud.brave.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author admin
 * @version 1.0
 * @description: 部门DTO
 * @date 2021/6/24 14:31
 */
@Data
public class SysDeptDTO implements Serializable {
    private static final long serialVersionUID = 5031138397441824683L;

    @ApiModelProperty(value = "部门id")
    private Long id;

    @ApiModelProperty(value = "父级部门id")
    private Long parentId;

    @ApiModelProperty(value = "部门名称")
    private String deptName;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "部门负责人")
    private String leader;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "部门邮箱")
    private String email;

    @ApiModelProperty(value = "部门使用状态（0：正常，1：禁用）")
    private String deptStatus;
}
