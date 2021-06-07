package com.cloud.core.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @ClassName: BaseEntity
 * @Description: 基础实体信息
 * @Author: yongchen
 * @Date: 2021/5/26 10:25
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity<T> extends BaseSuperEntuty<T> {
    private static final long serialVersionUID = -8993709623869040582L;

    /**
     * 更新时间
     **/
    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    protected LocalDateTime updateTime;

    /**
     * 更新用户
     **/
    @ApiModelProperty(value = "更新用户")
    @TableField(value = "update_user", fill = FieldFill.INSERT_UPDATE)
    protected T updateUser;

    public BaseEntity(Long id, LocalDateTime crateTime, T crateUser, LocalDateTime updateTime, T updateUser){
        super(id, crateTime, crateUser);
        this.updateTime = updateTime;
        this.updateUser = updateUser;
    }
}
