package com.example.todoSpringboot.common;

/**
 * ClassName: CommonResult
 * Package: com.example.todoSpringboot
 * Description:
 * User: 1953287730
 *
 * @Author 栗飞龙
 * @Create 2023/10/16 23:24
 * @Version 1.0
 */
import lombok.Data;

import java.io.Serializable;

@Data
public class CommonResult<T> implements Serializable {

    private Integer code;
    private String msg;
    private T data;


    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> result = new CommonResult<>();
        result.code = 200;
        result.data = data;
        result.msg = "操作成功";
        return result;
    }

    public static <T> CommonResult<T> error(Integer code, String message) {
        CommonResult<T> result = new CommonResult<>();
        result.code = code;
        result.msg = message;
        return result;
    }
}

