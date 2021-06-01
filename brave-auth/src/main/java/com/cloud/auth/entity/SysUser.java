package com.cloud.auth.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.cloud.core.db.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

/**
 * @ClassName: SysUser
 * @Description: 用户信息
 * @Author: yongchen
 * @Date: 2021/5/26 15:28
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SysUser extends BaseEntity<Long> {
    private static final long serialVersionUID = 7871276068583812120L;

    /**
     * 用户呢称
     **/
    @TableField("nick_name")
    @ApiModelProperty(value = "用户呢称")
    @Length(max = 20, message = "")
    private String nickName;

    /**
     * 用户姓名
     **/
    @TableField("user_name")
    @ApiModelProperty(value = "用户姓名")
    private String userName;

    /**
     * 用户密码
     **/
    @TableField("pass_word")
    @ApiModelProperty(value = "用户密码")
    private String passWord;

    /**
     * 用户手机号码
     **/
    @TableField("phone")
    @ApiModelProperty(value = "用户手机号码")
    private String phone;

    /**
     * 用户性别
     **/
    @TableField("sex")
    @ApiModelProperty(value = "用户性别")
    private String sex;

    /**
     * 用户生日
     **/
    @TableField("birthday")
    @ApiModelProperty(value = "用户生日")
    private String birthday;

    /**
     * 是否有效（0：无效 1：有效）
     **/
    @TableField("is_effective")
    @ApiModelProperty(value = "是否有效（0：无效 1：有效）")
    private String isEffective;

    /**
     * 是否删除（0：否；1：是）
     **/
    @TableField("del_state")
    @ApiModelProperty(value = "是否删除（0：否；1：是）")
    private String delState;

    @Builder
    public SysUser(Long id, LocalDateTime createTime, Long createUser, LocalDateTime updateTime, Long updateUser, String nickName, String userName, String passWord, String phone, String sex, String birthday, String isEffective, String delState) {
        this.id = id;
        this.createTime = createTime;
        this.createUser = createUser;
        this.updateTime = updateTime;
        this.updateUser = updateUser;
        this.nickName = nickName;
        this.userName = userName;
        this.passWord = passWord;
        this.phone = phone;
        this.sex = sex;
        this.birthday = birthday;
        this.isEffective = isEffective;
        this.delState = delState;
    }
}
