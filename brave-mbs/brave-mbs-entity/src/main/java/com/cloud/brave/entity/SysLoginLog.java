package com.cloud.brave.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.brave.core.mybatisplus.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yongchen
 * @since 2021-08-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_login_log")
@ApiModel(value="SysLoginLog对象", description="")
public class SysLoginLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "登录IP地址")
    @TableField("ip_addr")
    private String ipAddr;

    @ApiModelProperty(value = "登录城市地址")
    @TableField("city_addr")
    private String cityAddr;

    @ApiModelProperty(value = "登录状态（0：成功， 1：失败）")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "提示信息")
    @TableField("msg")
    private String msg;

    @ApiModelProperty(value = "设备类型")
    @TableField("device_type")
    private String deviceType;

    @ApiModelProperty(value = "浏览器")
    @TableField("browser")
    private String browser;

    @ApiModelProperty(value = "操作系统")
    @TableField("operate_system")
    private String operateSystem;

    @ApiModelProperty(value = "操作人姓名")
    @TableField("create_name")
    private String createName;


}
