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
 * 资源信息表
 * </p>
 *
 * @author yongchen
 * @since 2021-06-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_menu")
@ApiModel(value="SysMenu对象", description="资源信息表")
public class SysMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "上级资源")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty(value = "资源名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "资源编码")
    @TableField("code")
    private String code;

    @ApiModelProperty(value = "资源备注信息")
    @TableField("desc")
    private String desc;

    @ApiModelProperty(value = "资源类型（1:菜单; 2:按钮）")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "资源路径")
    @TableField("path")
    private String path;

    @ApiModelProperty(value = "资源权限")
    @TableField("authority")
    private String authority;

    @ApiModelProperty(value = "资源样式（0：当前页面打开 1：新页面打开）")
    @TableField("style")
    private String style;

    @ApiModelProperty(value = "资源图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "是否启用（0：是；1：否）")
    @TableField("is_enable")
    private String isEnable;

    @ApiModelProperty(value = "排序值")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty(value = "逻辑删除标识（0：正常；1：已删除）")
    @TableField("del_state")
    private String delState;


}
