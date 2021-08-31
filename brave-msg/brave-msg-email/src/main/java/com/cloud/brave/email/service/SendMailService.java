package com.cloud.brave.email.service;

import com.cloud.brave.email.entity.Mail;

/**
 * @author yongchen
 * @description: 邮件发送服务
 * @date 2021/8/23 16:11
 */
public interface SendMailService {

    /**
     * @description: 邮件发送服务
     * @param mail
     * @return: Boolean
     * @author yongchen
     * @date: 2021/8/23 16:20
     */
    Boolean sendMail(Mail mail);

    /**
     * @description: 邮箱验证邮件发送
     * @param mail
     * @return: java.lang.Boolean
     * @author yongchen
     * @date: 2021/8/24 11:16
     */
    Boolean sendMailVerify(Mail mail);
}
