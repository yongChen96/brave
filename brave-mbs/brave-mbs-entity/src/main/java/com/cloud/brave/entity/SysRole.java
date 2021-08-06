package com.cloud.brave.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.brave.mybatisplus.generator.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色信息表
 * </p>
 *
 * @author yongchen
 * @since 2021-06-23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_role")
@ApiModel(value="SysRole对象", description="角色信息表")
public class SysRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色名称")
    @TableField("role_name")
    private String roleName;

    @ApiModelProperty(value = "角色编码")
    @TableField("role_code")
    private String roleCode;

    @ApiModelProperty(value = "显示顺序")
    @TableField("role_sort")
    private Integer roleSort;

    @ApiModelProperty(value = "角色状态（0：正常，1：停用）")
    @TableField("role_status")
    private String roleStatus;

    @ApiModelProperty(value = "是否删除（0：否，1：是）")
    @TableField("del_state")
    private String delState;


}
