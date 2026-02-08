package com.example.demo.exception;

import com.example.demo.constant.ErrorCode;

/**
 * 系统异常
 * 用于处理系统内部、基础设施相关的异常情况
 */
public class SystemException extends BusinessException {

    public SystemException(ErrorCode errorCode) {
        super(errorCode);
    }

    public SystemException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public SystemException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public SystemException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public SystemException(Integer code, String message) {
        super(code, message);
    }

    public SystemException(Integer code, String message, Throwable cause) {
        super(code, message, cause);
    }
}