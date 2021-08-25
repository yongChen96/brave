package com.cloud.email.controller;

import com.cloud.brave.core.base.controller.BaseController;
import com.cloud.brave.core.result.Result;
import com.cloud.email.entity.Mail;
import com.cloud.email.service.SendMailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yongchen
 * @description: 邮件服务控制器
 * @date 2021/8/23 16:12
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
@Api(value = "SendMailController", tags = "邮件服务控制器")
public class SendMailController extends BaseController {

    private final SendMailService sendMailService;

    /**
     * @description: 发送普通邮件
     * @param mail
     * @return: com.cloud.brave.core.result.Result<java.lang.Boolean>
     * @author yongchen
     * @date: 2021/8/23 17:23
     */
    @PostMapping("/sendMail")
    @ApiOperation(value = "发送邮件", notes = "发送邮件")
    public Result<Boolean> sendMail(@RequestBody Mail mail){
        if (sendMailService.sendMail(mail)) {
            return success(true);
        }
        return failed("邮件发送失败");
    }

    /**
     * @description: 邮箱验证邮件发送
     * @param mail
     * @return: com.cloud.brave.core.result.Result<java.lang.Boolean> 
     * @author yongchen
     * @date: 2021/8/24 11:50
     */
    @PostMapping("/sendMailVerify")
    @ApiOperation(value = "邮箱验证邮件发送", notes = "邮箱验证邮件发送")
    public Result<Boolean> sendMailVerify(@RequestBody Mail mail){
        if (sendMailService.sendMailVerify(mail)) {
            return success(true);
        }
        return failed("邮件发送失败");
    }
}
