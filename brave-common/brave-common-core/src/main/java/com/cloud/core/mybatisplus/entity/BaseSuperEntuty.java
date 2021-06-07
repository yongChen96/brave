package com.cloud.core.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName: BaseSuperEntuty
 * @Description: 基础实体类
 * @Author: yongchen
 * @Date: 2021/5/26 10:25
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseSuperEntuty<T> implements Serializable {
    private static final long serialVersionUID = -7307649846608704913L;

    /**
     * 主键id
     **/
    @NotNull(message = "主键id不能为空")
    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.INPUT)
    protected Long id;

    /**
     * 创建时间
     **/
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    protected LocalDateTime createTime;

    /**
     * 创建用户
     **/
    @ApiModelProperty(value = "创建用户")
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    protected T createUser;
}
