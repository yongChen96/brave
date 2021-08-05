package com.cloud.gateway.component;

import cn.hutool.json.JSONUtil;
import com.cloud.brave.core.enums.ResultCodeEnums;
import com.cloud.brave.core.result.Result;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;

/**
 * @ClassName: ResponseUtils
 * @Description: 返回类
 * @Author: yongchen
 * @Date: 2021/6/15 16:41
 **/
public class ResponseUtils {

    public static Mono writeErrorInfo(ServerHttpResponse response, ResultCodeEnums resultCodeEnums) {
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.getHeaders().set("Access-Control-Allow-Origin", "*");
        response.getHeaders().set("Cache-Control", "no-cache");
        String body = JSONUtil.toJsonStr(Result.failed(resultCodeEnums.getCode(), resultCodeEnums.getMsg()));
        DataBuffer buffer = response.bufferFactory().wrap(body.getBytes(Charset.forName("UTF-8")));
        return response.writeWith(Mono.just(buffer))
                .doOnError(error -> DataBufferUtils.release(buffer));
    }
}
