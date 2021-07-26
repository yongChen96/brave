package com.cloud.brave.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.core.mybatisplus.entity.BaseEntity;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author yongchen
 * @since 2021-07-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(value="SysUser对象", description="用户信息表")
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "用户密码")
    @TableField("pass_word")
    private String passWord;

    @ApiModelProperty(value = "联系方式")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "性别（0：女，1：男）")
    @TableField("sex")
    private String sex;

    @ApiModelProperty(value = "生日")
    @TableField("birthday")
    private LocalDate birthday;

    @ApiModelProperty(value = "部门id")
    @TableField("dept_id")
    private Long deptId;

    @ApiModelProperty(value = "头像url")
    @TableField("avatar_url")
    private String avatarUrl;

    @ApiModelProperty(value = "是否锁定（0：否，1：是）")
    @TableField("is_lock")
    private String isLock;

    @ApiModelProperty(value = "是否在线（0：否，1：是）")
    @TableField("is_online")
    private String isOnline;

    @ApiModelProperty(value = "是否删除（0：否，1：是）")
    @TableField("del_state")
    private String delState;


}
