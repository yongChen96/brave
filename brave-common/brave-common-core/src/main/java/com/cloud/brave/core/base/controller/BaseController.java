package com.cloud.brave.core.base.controller;

import com.cloud.brave.core.result.Result;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: BaseController
 * @Description: 基础Controller配置
 * @Author: yongchen
 * @Date: 2021/6/1 16:45
 **/
public abstract class BaseController {

    @Resource
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;

    /**
     * @Author: yongchen
     * @Description: 返回成功
     * @Date: 16:50 2021/6/1
     * @Param: data 返回结果集
     * @return: com.cloud.core.result.Result<T>
     **/
    public <T> Result<T> success(T data){
        return Result.success(data);
    }

    /**
     * @Author: yongchen
     * @Description: 返回失败
     * @Date: 16:52 2021/6/1
     * @Param:
     * @return: com.cloud.core.result.Result<T>
     **/
    public <T> Result<T> failed(){
        return Result.failed();
    }

    /**
     * @Author: yongchen
     * @Description: 自定义错误信息返回
     * @Date: 16:53 2021/6/1
     * @Param: msg 错误信息
     * @return: com.cloud.core.result.Result<T>
     **/
    public <T> Result<T> failed(String msg){
        return Result.failed(msg);
    }

    /**
     * @Author: yongchen
     * @Description: 返回失败
     * @Date: 16:54 2021/6/1
     * @Param: code 状态码
     * @Param: msg  错误信息
     * @return: com.cloud.core.result.Result<T>
     **/
    public <T> Result<T> failed(Integer code, String msg){
        return Result.failed(code, msg);
    }

    /**
     * @Author: yongchen
     * @Description: 返回失败
     * @Date: 16:57 2021/6/1
     * @Param: data 失败结果集
     * @return: com.cloud.core.result.Result<T>
     **/
    public <T> Result<T> failed(T data){
        return Result.failed(data);
    }

    /**
     * @Author: yongchen
     * @Description: 返回失败
     * @Date: 16:58 2021/6/1
     * @Param: msg   失败信息
     * @Param: data  失败结果集
     * @return: com.cloud.core.result.Result<T>
     **/
    public <T> Result<T> failed(String msg, T data){
        return Result.failed(msg, data);
    }

}
