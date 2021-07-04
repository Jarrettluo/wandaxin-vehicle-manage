package com.example.demo.controller;

import com.example.demo.annotation.UserLoginToken;
import com.example.demo.aop.OperationLogAnnotation;
import com.example.demo.domain.dto.UserDTO;
import com.example.demo.service.TokenService;
import com.example.demo.service.UserService;
import com.example.utils.result.ApiResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    //登录
    @OperationLogAnnotation(operModul = "用户模块",operType = "登录", operDesc = "用户登录")
    @CrossOrigin
    @PostMapping("/login")
    public ApiResult login(@RequestBody UserDTO user){
        return userService.findByUsername(user);
    }

    @CrossOrigin
    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }

}
