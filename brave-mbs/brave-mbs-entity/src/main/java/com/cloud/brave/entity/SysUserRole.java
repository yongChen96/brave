package com.cloud.brave.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.core.mybatisplus.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.cloud.core.mybatisplus.entity.BaseSuperEntuty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户角色中间表
 * </p>
 *
 * @author yongchen
 * @since 2021-06-07
 */
@Data
@Accessors(chain = true)
@TableName("sys_user_role")
@ApiModel(value="SysUserRole对象", description="用户角色中间表")
public class SysUserRole extends BaseSuperEntuty<Long> {

    private static final long serialVersionUID = 6769883444279659584L;

    @ApiModelProperty(value = "用户表id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "角色id")
    @TableField("role_id")
    private Long roleId;
}
