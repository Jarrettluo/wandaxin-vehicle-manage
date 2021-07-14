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

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private HttpServletRequest request;


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

    @CrossOrigin
    @UserLoginToken
    @GetMapping("/save/")
    public ApiResult save(){
        return ApiResult.success();
    }

    @CrossOrigin
    @UserLoginToken
    @GetMapping("/list/")
    public ApiResult list(@RequestParam Long companyId) {
        return userService.list(companyId);
    }

    @CrossOrigin
    @UserLoginToken
    @PostMapping("/changePwd/")
    public ApiResult changePwd(@RequestBody Map<String, String> pwd) {
        return userService.changePwd(pwd);
    }

    @CrossOrigin
    @UserLoginToken
    @PostMapping("/changeType/")
    public ApiResult changeType(@RequestBody Map<String, String> type) {
        return userService.changeType(type);
    }

    @CrossOrigin
    @UserLoginToken
    @DeleteMapping("/")
    public ApiResult deleteUser(@RequestParam("userId") String userId) {
        return userService.deleteUser(userId);
    }

}
