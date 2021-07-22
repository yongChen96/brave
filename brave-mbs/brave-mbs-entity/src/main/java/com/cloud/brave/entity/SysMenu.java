package com.cloud.brave.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.core.mybatisplus.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author yongchen
 * @since 2021-07-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_menu")
@ApiModel(value="SysMenu对象", description="菜单权限表")
public class SysMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单名称")
    @TableField("menu_name")
    private String menuName;

    @ApiModelProperty(value = "父菜单ID")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty(value = "显示顺序")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty(value = "路由地址")
    @TableField("path")
    private String path;

    @ApiModelProperty(value = "组件路径")
    @TableField("component")
    private String component;

    @ApiModelProperty(value = "是否为外链（0：是，1：否）")
    @TableField("is_external_link")
    private String isExternalLink;

    @ApiModelProperty(value = "菜单类型（0：目录，1：菜单，2：按钮）")
    @TableField("menu_type")
    private String menuType;

    @ApiModelProperty(value = "显示状态（0：显示，1：隐藏）")
    @TableField("is_display")
    private String isDisplay;

    @ApiModelProperty(value = "菜单状态（0：启用，1：禁用）")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "权限标识")
    @TableField("perms")
    private String perms;

    @ApiModelProperty(value = "菜单图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "是否删除（0：否，1：是）")
    @TableField("del_state")
    private String delState;


}
