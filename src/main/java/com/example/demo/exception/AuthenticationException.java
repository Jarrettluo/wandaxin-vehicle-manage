package com.example.demo.exception;

import com.example.demo.constant.ErrorCode;

/**
 * 认证授权异常
 * 用于处理用户认证、授权相关的异常情况
 */
public class AuthenticationException extends BusinessException {

    public AuthenticationException(ErrorCode errorCode) {
        super(errorCode);
    }

    public AuthenticationException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public AuthenticationException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public AuthenticationException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public AuthenticationException(Integer code, String message) {
        super(code, message);
    }

    public AuthenticationException(Integer code, String message, Throwable cause) {
        super(code, message, cause);
    }
}