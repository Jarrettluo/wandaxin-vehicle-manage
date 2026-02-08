package com.example.demo.interceptor;

import com.example.demo.constant.ErrorCode;
import com.example.demo.exception.BusinessException;
import com.example.utils.result.ApiResult;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 全局异常处理器
 * 统一处理应用中的所有异常，返回规范的错误响应
 */
@Slf4j
@ControllerAdvice
public class GloablExceptionHandler {

    // ==================== 自定义业务异常处理 ====================

    /**
     * 处理自定义业务异常
     */
    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public ApiResult handleBusinessException(BusinessException e) {
        log.warn("业务异常: code={}, message={}", e.getCode(), e.getMessage(), e);
        return ApiResult.error(e.getCode(), e.getMessage());
    }

    // ==================== 参数验证异常处理 ====================

    /**
     * 处理@Valid注解参数验证异常
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String errorMessage = "参数错误";

        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            if (!errors.isEmpty()) {
                // 获取第一个错误信息
                FieldError fieldError = (FieldError) errors.get(0);
                errorMessage = fieldError.getDefaultMessage();
            }
        }

        log.warn("参数验证失败: {}", errorMessage, e);
        return ApiResult.error(ErrorCode.PARAM_VALIDATION_ERROR.getCode(), errorMessage);
    }

    /**
     * 处理@Validated注解方法参数验证异常
     */
    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public ApiResult handleConstraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        String errorMessage = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));

        log.warn("参数约束验证失败: {}", errorMessage, e);
        return ApiResult.error(ErrorCode.PARAM_VALIDATION_ERROR.getCode(), errorMessage);
    }

    /**
     * 处理缺少请求参数异常
     */
    @ResponseBody
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ApiResult handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        String errorMessage = String.format("缺少必要参数: %s", e.getParameterName());
        log.warn("缺少请求参数: {}", errorMessage, e);
        return ApiResult.error(ErrorCode.PARAM_MISSING.getCode(), errorMessage);
    }

    /**
     * 处理参数类型不匹配异常
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ApiResult handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        String errorMessage = String.format("参数类型错误: %s，期望类型: %s",
                e.getName(), e.getRequiredType() != null ? e.getRequiredType().getSimpleName() : "未知");
        log.warn("参数类型不匹配: {}", errorMessage, e);
        return ApiResult.error(ErrorCode.PARAM_TYPE_ERROR.getCode(), errorMessage);
    }

    // ==================== HTTP请求相关异常处理 ====================

    /**
     * 处理HTTP方法不支持异常
     */
    @ResponseBody
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ApiResult handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        String errorMessage = String.format("不支持的HTTP方法: %s，支持的方法: %s",
                e.getMethod(), String.join(", ", e.getSupportedMethods()));
        log.warn("HTTP方法不支持: {}", errorMessage, e);
        return ApiResult.error(ErrorCode.OPERATION_NOT_ALLOWED.getCode(), errorMessage);
    }

    /**
     * 处理HTTP媒体类型不支持异常
     */
    @ResponseBody
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ApiResult handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        String errorMessage = String.format("不支持的媒体类型: %s", e.getContentType());
        log.warn("HTTP媒体类型不支持: {}", errorMessage, e);
        return ApiResult.error(ErrorCode.PARAM_TYPE_ERROR.getCode(), errorMessage);
    }

    /**
     * 处理HTTP消息不可读异常（如JSON解析错误）
     */
    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiResult handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        String errorMessage = "请求体解析失败，请检查JSON格式";
        log.warn("HTTP消息不可读: {}", errorMessage, e);
        return ApiResult.error(ErrorCode.PARAM_FORMAT_ERROR.getCode(), errorMessage);
    }

    /**
     * 处理404未找到处理器异常
     */
    @ResponseBody
    @ExceptionHandler(NoHandlerFoundException.class)
    public ApiResult handleNoHandlerFoundException(NoHandlerFoundException e) {
        String errorMessage = String.format("接口不存在: %s %s", e.getHttpMethod(), e.getRequestURL());
        log.warn("接口不存在: {}", errorMessage, e);
        return ApiResult.error(ErrorCode.DATA_NOT_FOUND.getCode(), errorMessage);
    }

    // ==================== 数据访问异常处理 ====================

    /**
     * 处理数据库访问异常
     */
    @ResponseBody
    @ExceptionHandler(DataAccessException.class)
    public ApiResult handleDataAccessException(DataAccessException e) {
        log.error("数据库访问异常", e);
        return ApiResult.error(ErrorCode.DATABASE_ERROR.getCode(), "数据库操作失败，请稍后重试");
    }

    // ==================== 通用异常处理 ====================

    /**
     * 处理所有未明确处理的异常
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ApiResult handleException(Exception e) {
        log.error("系统异常", e);

        // 根据异常类型返回不同的错误信息
        String errorMessage = e.getMessage();
        if (errorMessage == null || errorMessage.trim().isEmpty()) {
            errorMessage = "服务器内部错误";
        }

        return ApiResult.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), errorMessage);
    }

    // ==================== 兼容历史错误码 ====================

    /**
     * 保持与历史代码兼容，原有的参数错误码1201
     * 原有的服务器错误码1203
     * 这些错误码已整合到ErrorCode枚举中
     */
}

