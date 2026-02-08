package com.example.demo.constant;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 错误码枚举类
 * 错误码规则：4位数字
 * 10xx: 认证授权相关错误
 * 11xx: 参数验证相关错误
 * 12xx: 业务逻辑相关错误
 * 13xx: 系统内部相关错误
 * 14xx: 第三方服务相关错误
 */
@Getter
public enum ErrorCode {

    // ========== 认证授权错误 (10xx) ==========
    UNAUTHORIZED(1001, "未授权访问", HttpStatus.UNAUTHORIZED),
    TOKEN_MISSING(1002, "访问令牌缺失", HttpStatus.UNAUTHORIZED),
    TOKEN_INVALID(1003, "访问令牌无效或已过期", HttpStatus.UNAUTHORIZED),
    TOKEN_EXPIRED(1004, "访问令牌已过期", HttpStatus.UNAUTHORIZED),
    ACCESS_DENIED(1005, "权限不足，拒绝访问", HttpStatus.FORBIDDEN),
    LOGIN_FAILED(1006, "用户名或密码错误", HttpStatus.UNAUTHORIZED),
    USER_NOT_FOUND(1007, "用户不存在", HttpStatus.UNAUTHORIZED),
    ACCOUNT_DISABLED(1008, "账户已禁用", HttpStatus.FORBIDDEN),
    ACCOUNT_LOCKED(1009, "账户已锁定", HttpStatus.FORBIDDEN),

    // ========== 参数验证错误 (11xx) ==========
    PARAM_VALIDATION_ERROR(1101, "参数验证失败", HttpStatus.BAD_REQUEST),
    PARAM_MISSING(1102, "缺少必要参数", HttpStatus.BAD_REQUEST),
    PARAM_TYPE_ERROR(1103, "参数类型错误", HttpStatus.BAD_REQUEST),
    PARAM_FORMAT_ERROR(1104, "参数格式错误", HttpStatus.BAD_REQUEST),
    PARAM_OUT_OF_RANGE(1105, "参数超出允许范围", HttpStatus.BAD_REQUEST),

    // ========== 业务逻辑错误 (12xx) ==========
    BUSINESS_ERROR(1201, "业务逻辑错误", HttpStatus.BAD_REQUEST),
    DATA_NOT_FOUND(1202, "数据不存在", HttpStatus.NOT_FOUND),
    DATA_ALREADY_EXISTS(1203, "数据已存在", HttpStatus.CONFLICT),
    OPERATION_NOT_ALLOWED(1204, "操作不允许", HttpStatus.METHOD_NOT_ALLOWED),
    RESOURCE_UNAVAILABLE(1205, "资源不可用", HttpStatus.SERVICE_UNAVAILABLE),
    INVALID_OPERATION(1206, "无效的操作", HttpStatus.BAD_REQUEST),

    // ========== 系统内部错误 (13xx) ==========
    INTERNAL_SERVER_ERROR(1301, "服务器内部错误", HttpStatus.INTERNAL_SERVER_ERROR),
    DATABASE_ERROR(1302, "数据库操作失败", HttpStatus.INTERNAL_SERVER_ERROR),
    FILE_OPERATION_ERROR(1303, "文件操作失败", HttpStatus.INTERNAL_SERVER_ERROR),
    NETWORK_ERROR(1304, "网络通信错误", HttpStatus.INTERNAL_SERVER_ERROR),
    CONFIGURATION_ERROR(1305, "系统配置错误", HttpStatus.INTERNAL_SERVER_ERROR),

    // ========== 第三方服务错误 (14xx) ==========
    EXTERNAL_SERVICE_ERROR(1401, "外部服务调用失败", HttpStatus.BAD_GATEWAY),
    EXTERNAL_SERVICE_TIMEOUT(1402, "外部服务调用超时", HttpStatus.GATEWAY_TIMEOUT),
    BAIDU_AI_ERROR(1403, "百度AI服务调用失败", HttpStatus.BAD_GATEWAY),
    EMAIL_SERVICE_ERROR(1404, "邮件服务调用失败", HttpStatus.BAD_GATEWAY),

    // ========== 兼容历史错误码 ==========
    // 保持与现有代码兼容
    PARAM_ERROR(1201, "参数错误，请检查", HttpStatus.BAD_REQUEST),          // 原有1201
    SERVER_ERROR(1203, "服务器出错", HttpStatus.INTERNAL_SERVER_ERROR);   // 原有1203

    /**
     * 错误码
     */
    private final Integer code;

    /**
     * 错误信息
     */
    private final String message;

    /**
     * HTTP状态码
     */
    private final HttpStatus httpStatus;

    ErrorCode(Integer code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    /**
     * 根据错误码获取枚举
     */
    public static ErrorCode fromCode(Integer code) {
        for (ErrorCode errorCode : values()) {
            if (errorCode.getCode().equals(code)) {
                return errorCode;
            }
        }
        return INTERNAL_SERVER_ERROR;
    }

    /**
     * 判断是否为认证授权错误
     */
    public boolean isAuthenticationError() {
        return this.code >= 1000 && this.code < 1100;
    }

    /**
     * 判断是否为参数验证错误
     */
    public boolean isValidationError() {
        return this.code >= 1100 && this.code < 1200;
    }

    /**
     * 判断是否为业务逻辑错误
     */
    public boolean isBusinessError() {
        return this.code >= 1200 && this.code < 1300;
    }

    /**
     * 判断是否为系统内部错误
     */
    public boolean isSystemError() {
        return this.code >= 1300 && this.code < 1400;
    }

    /**
     * 判断是否为第三方服务错误
     */
    public boolean isExternalServiceError() {
        return this.code >= 1400 && this.code < 1500;
    }
}