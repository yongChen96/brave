package com.cloud.brave.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.brave.mybatisplus.generator.entity.BaseEntity;
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
@ExcelTarget("sysLog")
@ApiModel(value="SysLog对象", description="")
public class SysLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Excel(name = "日志类型")
    @ApiModelProperty(value = "日志类型（0：正常，1：异常）")
    @TableField("type")
    private String type;

    @Excel(name = "日志描述")
    @ApiModelProperty(value = "日志描述")
    @TableField("description")
    private String description;

    @Excel(name = "请求uri")
    @ApiModelProperty(value = "请求uri")
    @TableField("request_uri")
    private String requestUri;

    @Excel(name = "请求IP地址")
    @ApiModelProperty(value = "请求IP地址")
    @TableField("request_ip")
    private String requestIp;

    @Excel(name = "请求城市")
    @ApiModelProperty(value = "请求城市")
    @TableField("request_city")
    private String requestCity;

    @Excel(name = "请求类型")
    @ApiModelProperty(value = "请求类型")
    @TableField("http_method")
    private String httpMethod;

    @Excel(name = "请求参数")
    @ApiModelProperty(value = "请求参数")
    @TableField("params")
    private String params;

    @Excel(name = "返回结果")
    @ApiModelProperty(value = "返回结果")
    @TableField("result")
    private String result;

    @Excel(name = "异常详情信息")
    @ApiModelProperty(value = "异常详情信息")
    @TableField("error_desc")
    private String errorDesc;

    @Excel(name = "异常描述")
    @ApiModelProperty(value = "异常描述")
    @TableField("error_detail")
    private String errorDetail;

    @Excel(name = "请求发起时间")
    @ApiModelProperty(value = "请求发起时间")
    @TableField("start_time")
    private String startTime;

    @Excel(name = "请求完成时间")
    @ApiModelProperty(value = "请求完成时间")
    @TableField("finish_time")
    private LocalDateTime finishTime;

    @Excel(name = "设备类型")
    @ApiModelProperty(value = "设备类型")
    @TableField("device_type")
    private String deviceType;

    @Excel(name = "浏览器")
    @ApiModelProperty(value = "浏览器")
    @TableField("browser")
    private String browser;

    @Excel(name = "操作系统")
    @ApiModelProperty(value = "操作系统")
    @TableField("operate_system")
    private String operateSystem;

    @Excel(name = "操作人姓名")
    @ApiModelProperty(value = "操作人姓名")
    @TableField("create_name")
    private String createName;


}
