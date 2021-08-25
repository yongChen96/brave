package com.cloud.email.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author yongchen
 * @description: 邮件实体类
 * @date 2021/8/23 16:15
 */
@Data
@ApiModel(value = "邮件实体类")
public class Mail implements Serializable {
    private static final long serialVersionUID = 5528325647943707394L;

    /**
     * 单个接收人
     */
    @ApiModelProperty(value = "单个接收人")
    private String recipient;

    /**
     * 接收人名称
     */
    @ApiModelProperty(value = "接收人名称")
    private String recipientName;

    /**
     * 多个接收人
     */
    @ApiModelProperty(value = "多个接收人")
    private String[] recipients;

    /**
     * 邮件主题
     */
    @ApiModelProperty(value = "邮件主题")
    @NotNull(message = "邮件主题不能为空")
    private String subject;

    /**
     * 邮件内容
     */
    @NotNull(message = "邮件内容不能为空")
    @ApiModelProperty(value = "邮件内容")
    private String content;
}
