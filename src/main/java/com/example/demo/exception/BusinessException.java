package com.example.demo.exception;

import com.example.demo.constant.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 业务异常基类
 * 所有自定义业务异常都应继承此类
 */
@Getter
public class BusinessException extends RuntimeException {

    /**
     * 错误码
     */
    private final Integer code;

    /**
     * HTTP状态码
     */
    private final HttpStatus httpStatus;

    /**
     * 构造方法 - 使用错误码枚举
     */
    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.httpStatus = errorCode.getHttpStatus();
    }

    /**
     * 构造方法 - 使用错误码枚举和自定义消息
     */
    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
        this.httpStatus = errorCode.getHttpStatus();
    }

    /**
     * 构造方法 - 使用错误码枚举、自定义消息和原因
     */
    public BusinessException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.code = errorCode.getCode();
        this.httpStatus = errorCode.getHttpStatus();
    }

    /**
     * 构造方法 - 使用错误码枚举和原因
     */
    public BusinessException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.code = errorCode.getCode();
        this.httpStatus = errorCode.getHttpStatus();
    }

    /**
     * 构造方法 - 兼容历史代码，直接使用错误码和消息
     */
    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    /**
     * 构造方法 - 兼容历史代码，直接使用错误码、消息和原因
     */
    public BusinessException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    /**
     * 获取错误码枚举
     */
    public ErrorCode getErrorCode() {
        return ErrorCode.fromCode(this.code);
    }

    @Override
    public String toString() {
        return "BusinessException{" +
                "code=" + code +
                ", message='" + getMessage() + '\'' +
                ", httpStatus=" + httpStatus +
                '}';
    }
}