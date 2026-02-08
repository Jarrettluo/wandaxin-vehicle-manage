package com.example.utils.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;

/**
 * 通用返回体.
 * https://github.com/KimZing/kimzing-utils/blob/9952c1c36af5c2ba78102632c697c2c6fac54bad/src/main/java/com/kimzing/utils/result/ApiResult.java
 * @author KimZing - kimzing@163.com
 * @since 2019/12/4 15:04
 * @param <T> 数据泛型类型
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResult<T>(Long timestamp, Integer code, String message, T data) implements Serializable {

    private static final long serialVersionUID = 2L;

    /**
     * 创建成功返回体，无数据
     *
     * @return ApiResult<Void>
     */
    public static ApiResult<Void> success() {
        return new ApiResult<>(System.currentTimeMillis(), 200, null, null);
    }

    /**
     * 创建成功返回体，包含数据
     *
     * @param data 数据体
     * @return ApiResult<T>
     */
    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<>(System.currentTimeMillis(), 200, null, data);
    }

    /**
     * 创建错误返回体
     *
     * @param code 错误码
     * @param message 错误信息
     * @return ApiResult<Void>
     */
    public static ApiResult<Void> error(Integer code, String message) {
        return new ApiResult<>(System.currentTimeMillis(), code, message, null);
    }

    /**
     * 获取状态码，兼容原有代码
     *
     * @return 状态码
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 获取时间戳，兼容原有代码
     *
     * @return 时间戳
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * 获取错误信息，兼容原有代码
     *
     * @return 错误信息
     */
    public String getMessage() {
        return message;
    }

    /**
     * 获取数据，兼容原有代码
     *
     * @return 数据
     */
    public T getData() {
        return data;
    }

    /**
     * 判断是否成功
     *
     * @return 是否成功
     */
    public boolean isSuccess() {
        return code == 200;
    }
}
