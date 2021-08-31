package com.cloud.brave.mq.controller;

import com.cloud.brave.core.base.controller.BaseController;
import com.cloud.brave.core.constant.AmqpConstants;
import com.cloud.brave.core.result.Result;
import com.cloud.brave.mq.service.MqSendMsgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yongchen
 * @description: 消息发布控制器
 * @date 2021/8/20 17:30
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/mq")
@Api(value = "MqSendMsgController", tags = "MQ消息发布控制器")
public class MqSendMsgController extends BaseController {

    private final MqSendMsgService mqSendMsgService;

    /**
     * @param exchange
     * @param router
     * @param msg
     * @description: 发送消息到MQ
     * @return: com.cloud.brave.core.result.Result<java.lang.Boolean>
     * @author yongchen
     * @date: 2021/8/20 18:00
     */
    @GetMapping("/sendMessage")
    @ApiOperation(value = "发送消息到MQ", notes = "发送消息到MQ")
    public Result<Boolean> sendMessage(@RequestParam(value = "exchange", required = false) String exchange,
                                       @RequestParam(value = "router") String router,
                                       @RequestParam(value = "msg") String msg) {
        if (mqSendMsgService.sendMessage(StringUtils.isNotBlank(exchange) ? exchange : AmqpConstants.RABBITMQ_BRAVE_DIRECT_EXCHANGE, router, msg)) {
            return success(true);
        }
        return failed("发布消息失败");
    }

}
