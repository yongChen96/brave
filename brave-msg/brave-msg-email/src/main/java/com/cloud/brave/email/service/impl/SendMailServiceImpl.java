package com.cloud.brave.email.service.impl;

import com.cloud.brave.core.exception.BraveException;
import com.cloud.brave.email.entity.Mail;
import com.cloud.brave.email.service.SendMailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;

/**
 * @author yongchen
 * @description: 邮件发送服务
 * @date 2021/8/23 16:12
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SendMailServiceImpl implements SendMailService {

    @Value("${spring.mail.sender}")
    private String sender;
    @Value("${spring.mail.nick-name}")
    private String nickName;

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    /**
     * @param mail
     * @description: 邮件发送服务
     * @return: Boolean
     * @author yongchen
     * @date: 2021/8/23 16:21
     */
    @Override
    public Boolean sendMail(Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(String.format("%s<%s>", nickName, sender));
        mailMessage.setTo(mail.getRecipients());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getContent());
        try {
            mailSender.send(mailMessage);
            return true;
        } catch (Exception e) {
            log.error("发送简单邮件时发生异常!", e);
            throw new BraveException(e.getMessage());
        }
    }

    /**
     * @param mail
     * @description: 邮箱验证邮件发送
     * @return: java.lang.Boolean
     * @author yongchen
     * @date: 2021/8/24 11:16
     */
    @Override
    public Boolean sendMailVerify(Mail mail) {
        if(StringUtils.isBlank(mail.getRecipientName())){
            throw new BraveException("用户姓名不能为空");
        }
        if (StringUtils.isBlank(mail.getRecipient())){
            throw new BraveException("收件人不能为空");
        }
        Context context = new Context();
        context.setVariable("code", mail.getContent());
        context.setVariable("userName", mail.getRecipientName());
        String process = templateEngine.process("braveVerifyMail", context);
        MimeMessage message = mailSender.createMimeMessage();
        try{
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(String.format("%s<%s>", nickName, sender));
            messageHelper.setTo(mail.getRecipient());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(process, true);
            FileSystemResource file = new FileSystemResource(new ClassPathResource("/imgs/brave.png").getFile());
            messageHelper.addInline("brave", file);
            mailSender.send(message);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            throw new BraveException(e.getMessage());
        }
    }
}
