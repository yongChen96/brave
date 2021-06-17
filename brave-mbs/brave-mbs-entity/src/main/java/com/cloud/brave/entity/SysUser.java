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
 * @since 2021-06-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(value="SysUser对象", description="用户信息表")
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户呢称")
    @TableField("nick_name")
    private String nickName;

    @ApiModelProperty(value = "用户名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "用户密码")
    @TableField("pass_word")
    private String passWord;

    @ApiModelProperty(value = "用户手机号码")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "用户性别")
    @TableField("sex")
    private String sex;

    @ApiModelProperty(value = "用户生日")
    @TableField("birthday")
    private LocalDate birthday;

    @ApiModelProperty(value = "用户头像")
    @TableField("avatar_url")
    private String avatarUrl;

    @ApiModelProperty(value = "账号是否锁定（0：正常；1：锁定）")
    @TableField("is_lock")
    private String isLock;

    @ApiModelProperty(value = "是否删除（0：否；1：是）")
    @TableField("del_state")
    private String delState;


}
