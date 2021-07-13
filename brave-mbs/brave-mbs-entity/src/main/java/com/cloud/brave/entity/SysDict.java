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
 * 字典数据表
 * </p>
 *
 * @author yongchen
 * @since 2021-07-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_dict")
@ApiModel(value="SysDict对象", description="字典数据表")
public class SysDict extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典名称")
    @TableField("dict_name")
    private String dictName;

    @ApiModelProperty(value = "字典编码")
    @TableField("dict_code")
    private String dictCode;

    @ApiModelProperty(value = "字典标签")
    @TableField("dict_label")
    private String dictLabel;

    @ApiModelProperty(value = "字典键值")
    @TableField("dict_value")
    private String dictValue;

    @ApiModelProperty(value = "样式属性（其他样式扩展）")
    @TableField("css_class")
    private String cssClass;

    @ApiModelProperty(value = "表格回显样式")
    @TableField("list_class")
    private String listClass;

    @ApiModelProperty(value = "是否默认（Y是 N否）")
    @TableField("is_default")
    private String isDefault;

    @ApiModelProperty(value = "状态（0正常 1停用）")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "字典排序")
    @TableField("dict_sort")
    private Integer dictSort;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "是否删除（0：否，1：是）")
    @TableField("del_state")
    private String delState;


}
