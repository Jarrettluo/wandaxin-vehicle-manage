package com.example.demo.controller;

import com.example.utils.result.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/finduser")
    public ApiResult find(){
        return ApiResult.success();
    }

    @GetMapping("/list")
    public ApiResult list(){
        return ApiResult.success();
    }

    @PostMapping("/add")
    public ApiResult add(){
        return ApiResult.success();
    }

}
