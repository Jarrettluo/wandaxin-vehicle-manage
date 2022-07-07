package com.example.demo.interceptor;

import com.example.utils.result.ApiResult;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
public class GloablExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ApiResult handleException(Exception e) {
        String msg = e.getMessage();
        if (msg == null || msg.equals("")) {
            msg = "服务器出错";
        }
        return ApiResult.error(1203, msg);
    }

    /**
     * 该全局异常拦截用于拦截valid注解的错误
     * @param e 方法校验错误
     * @return 返回ApiResult
     */
//    @ResponseStatus(HttpStatus.BAD_REQUEST) //设置状态码为 400
    @ResponseBody
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ApiResult paramExceptionHandler(MethodArgumentNotValidException e) {
        BindingResult exceptions = e.getBindingResult();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);
                return ApiResult.error(1201, fieldError.getDefaultMessage());
            }
        }
        return ApiResult.error(1201, "参数错误，请检查");
    }


}

