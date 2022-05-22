package com.example.demo.controller;

import com.example.demo.annotation.UserLoginToken;
import com.example.demo.aop.OperationLogAnnotation;
import com.example.demo.domain.dto.UserDTO;
import com.example.demo.service.UserService;
import com.example.utils.result.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Qualifier("UserService")
    @Autowired
    UserService userService;

    @Autowired
    private HttpServletRequest request;


    //登录
    @OperationLogAnnotation(operModul = "用户系统",operType = "登录", operDesc = "用户登录")

    @PostMapping("/login")
    public ApiResult login(@RequestBody UserDTO user){
        return userService.findByUsername(user);
    }


    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }

    @UserLoginToken
    @GetMapping("/save/")
    public ApiResult save(){
        return ApiResult.success();
    }

    @UserLoginToken
    @GetMapping("/list/")
    public ApiResult list(@RequestParam Long companyId) {
        return userService.list(companyId);
    }

    @UserLoginToken
    @PostMapping("/changePwd/")
    public ApiResult changePwd(@RequestBody Map<String, String> pwd) {
        return userService.changePwd(pwd);
    }

    @UserLoginToken
    @PostMapping("/changeType/")
    public ApiResult changeType(@RequestBody Map<String, String> type) {
        return userService.changeType(type);
    }

    @UserLoginToken
    @DeleteMapping("/")
    public ApiResult deleteUser(@RequestParam("userId") String userId) {
        return userService.deleteUser(userId);
    }

}
