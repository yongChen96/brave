package com.cloud.log.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName: BraveSysLog
 * @Description: 日志entity
 * @Author: yongchen
 * @Date: 2021/5/21 14:16
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysLog implements Serializable {
    private static final long serialVersionUID = 4841598704732722838L;

    /**
     * 主键
     **/
    private Long id;

    /**
     * 日志类型
     **/
    private String type;

    /**
     * 日志描述
     **/
    private String description;

    /**
     * 服务id
     **/
    private String serviceId;

    /**
     * 请求IP
     **/
    private String requestIp;

    /**
     * 请求uri
     **/
    private String requestUri;

    /**
     * 请求方法类型
     **/
    private String httpMethod;

    /**
     * 请求参数
     **/
    private String params;

    /**
     * 返回结果
     **/
    private String result;

    /**
     * 异常详细描述
     **/
    private String errorDesc;

    /**
     * 异常描述
     **/
    private String errorDetail;

    /**
     * 开始时间
     **/
    private LocalDateTime startTime;

    /**
     * 完成时间
     **/
    private LocalDateTime finishTime;

    /**
     * 浏览器信息
     **/
    private String browser;

    /**
     * 操作人id
     **/
    private String createUser;

    /**
     * 操作人姓名
     **/
    private String createName;

    /**
     * 操作时间
     **/
    private LocalDateTime createTime;
}
