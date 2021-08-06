package com.cloud.brave.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.brave.mybatisplus.generator.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 部门信息表
 * </p>
 *
 * @author yongchen
 * @since 2021-06-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_dept")
@ApiModel(value="SysDept对象", description="部门信息表")
public class SysDept extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "父级部门id")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty(value = "部门名称")
    @TableField("dept_name")
    private String deptName;

    @ApiModelProperty(value = "完整id路劲")
    @TableField("id_path")
    private String idPath;

    @ApiModelProperty(value = "完成名称路径")
    @TableField("name_path")
    private String namePath;

    @ApiModelProperty(value = "排序")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty(value = "部门负责人")
    @TableField("leader")
    private String leader;

    @ApiModelProperty(value = "联系电话")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "部门邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "部门使用状态（0：正常，1：禁用）")
    @TableField("dept_status")
    private String deptStatus;

    @ApiModelProperty(value = "是否删除（0：否，1：是）")
    @TableField("del_state")
    private String delState;


}
