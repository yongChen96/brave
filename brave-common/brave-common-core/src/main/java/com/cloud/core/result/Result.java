package com.cloud.core.result;

import com.cloud.core.enums.ResultCodeEnums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName: Result
 * @Description: 响应主体信息
 * @Author: yongchen
 * @Date: 2021/5/21 11:12
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -5263929428884146474L;

    /**
     * 响应状态码
     **/
    private Integer code;
    /**
     * 响应信息
     **/
    private String msg;
    /**
     * 响应数据
     **/
    private T data;

    /**
     * @Author: yongchen
     * @Description: 无数据返回操作成功
     * @Date: 11:25 2021/5/21
     * @Param:
     * @return: com.cloud.core.result.Result<T>
     **/
    public static <T> Result<T> success() {
        return new Result<>(ResultCodeEnums.SUCCESS.getCode(), ResultCodeEnums.SUCCESS.getMsg(), null);
    }

    /**
     * @Author: yongchen
     * @Description: 携带数据返回操作成功
     * @Date: 11:25 2021/5/21
     * @Param: data   响应数据
     * @return: com.cloud.core.result.Result<T>
     **/
    public static <T> Result<T> success(T data) {
        return new Result(ResultCodeEnums.SUCCESS.getCode(), ResultCodeEnums.SUCCESS.getMsg(), data);
    }

    /**
     * @Author: yongchen
     * @Description: 携带数据且自定义响应信息返回操作成功
     * @Date: 11:27 2021/5/21
     * @Param: msg    响应信息
     * @Param: data   响应数据
     * @return: com.cloud.core.result.Result<T>
     **/
    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(ResultCodeEnums.SUCCESS.getCode(), msg, data);
    }

    /**
     * @Author: yongchen
     * @Description: 无响应数据返回操作失败
     * @Date: 11:30 2021/5/21
     * @Param:
     * @return: com.cloud.core.result.Result<T>
     **/
    public static <T> Result<T> failed() {
        return new Result<>(ResultCodeEnums.FAILED.getCode(), ResultCodeEnums.FAILED.getMsg(), null);
    }

    /**
     * @Author: yongchen
     * @Description: 自定义响应信息返回操作失败
     * @Date: 11:31 2021/5/21
     * @Param: msg   响应信息
     * @return: com.cloud.core.result.Result<T>
     **/
    public static <T> Result<T> filed(String msg) {
        return new Result<>(ResultCodeEnums.FAILED.getCode(), msg, null);
    }

    /**
     * @Author: yongchen
     * @Description: 携带响应数据返回操作失败
     * @Date: 11:31 2021/5/21
     * @Param: data   响应数据
     * @return: com.cloud.core.result.Result<T>
     **/
    public static <T> Result<T> filed(T data) {
        return new Result<>(ResultCodeEnums.FAILED.getCode(), ResultCodeEnums.FAILED.getMsg(), data);
    }

    /**
     * @Author: yongchen
     * @Description: 携带响应数据且自定义响应信息返回操作失败
     * @Date: 11:31 2021/5/21
     * @Param: msg    响应信息
     * @Param: data   响应数据
     * @return: com.cloud.core.result.Result<T>
     **/
    public static <T> Result<T> filed(String msg, T data) {
        return new Result<>(ResultCodeEnums.FAILED.getCode(), msg, data);
    }
}
