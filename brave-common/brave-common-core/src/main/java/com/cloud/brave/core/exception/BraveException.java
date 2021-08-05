package com.cloud.brave.core.exception;


import lombok.Data;

/**
 * @ClassName: BraveException
 * @Description: 区局异常处理
 * @Author: yongchen
 * @Date: 2021/5/21 11:08
 **/
@Data
public class BraveException extends RuntimeException {
    private static final long serialVersionUID = 4360304445378993573L;

    /**
     * 响应状态码
     **/
    private Integer code;
    /**
     * 响应信息
     **/
    private String message;

    public BraveException(String message) {
        super(message);
        this.code = -99;
        this.message = message;
    }

    public BraveException(Integer code, String message, Object... args) {
        super(String.format(message, args));
        this.code = code;
        this.message = String.format(message, args);
    }

}
