package com.cloud.brave.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.brave.core.mybatisplus.entity.BaseEntity;
import java.time.LocalDateTime;
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
@TableName("sys_log")
@ApiModel(value="SysLog对象", description="")
public class SysLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "日志类型（0：正常，1：异常）")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "日志描述")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "请求uri")
    @TableField("request_uri")
    private String requestUri;

    @ApiModelProperty(value = "请求IP地址")
    @TableField("request_ip")
    private String requestIp;

    @ApiModelProperty(value = "请求城市")
    @TableField("request_city")
    private String requestCity;

    @ApiModelProperty(value = "请求类型")
    @TableField("http_method")
    private String httpMethod;

    @ApiModelProperty(value = "请求参数")
    @TableField("params")
    private String params;

    @ApiModelProperty(value = "返回结果")
    @TableField("result")
    private String result;

    @ApiModelProperty(value = "异常详情信息")
    @TableField("error_desc")
    private String errorDesc;

    @ApiModelProperty(value = "异常描述")
    @TableField("error_detail")
    private String errorDetail;

    @ApiModelProperty(value = "请求发起时间")
    @TableField("start_time")
    private String startTime;

    @ApiModelProperty(value = "请求完成时间")
    @TableField("finish_time")
    private LocalDateTime finishTime;

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
