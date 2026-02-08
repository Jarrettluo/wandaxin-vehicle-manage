package com.example.demo.exception;

import com.example.demo.constant.ErrorCode;

/**
 * 参数验证异常
 * 用于处理参数校验、数据验证相关的异常情况
 */
public class ValidationException extends BusinessException {

    public ValidationException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ValidationException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public ValidationException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public ValidationException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public ValidationException(Integer code, String message) {
        super(code, message);
    }

    public ValidationException(Integer code, String message, Throwable cause) {
        super(code, message, cause);
    }
}