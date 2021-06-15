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
    @Autowired
    TokenService tokenService;

    //登录
    @OperationLogAnnotation(operModul = "用户模块",operType = "登录", operDesc = "用户登录")
    @CrossOrigin
    @PostMapping("/login")
    public ApiResult login(@RequestBody UserDTO user){
        UserDTO userForBase=userService.findByUsername(user);
        if(userForBase==null){
            return ApiResult.error(1201,"登录失败,用户不存在");
        }else {
            if (!userForBase.getPassword().equals(user.getPassword())){
                return ApiResult.error(1202,"登录失败,密码错误");
            }else {
                String token = tokenService.getToken(userForBase);
                userForBase.setPassword("");
                HashMap<String, Object> k = new HashMap<String, Object>();
                k.put("token", token);
                k.put("user", userForBase);
                return ApiResult.success(k);
            }
        }
    }

    @CrossOrigin
    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }

}
