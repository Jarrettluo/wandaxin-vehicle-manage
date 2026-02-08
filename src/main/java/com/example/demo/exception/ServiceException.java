package com.example.demo.exception;

import com.example.demo.constant.ErrorCode;

/**
 * 服务层异常
 * 用于处理业务逻辑、服务调用相关的异常情况
 */
public class ServiceException extends BusinessException {

    public ServiceException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ServiceException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public ServiceException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public ServiceException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public ServiceException(Integer code, String message) {
        super(code, message);
    }

    public ServiceException(Integer code, String message, Throwable cause) {
        super(code, message, cause);
    }
}