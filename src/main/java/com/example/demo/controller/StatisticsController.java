package com.example.demo.controller;

import com.example.demo.aop.OperationLogAnnotation;
import com.example.demo.service.StatictisService;
import com.example.utils.result.ApiResult;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/13 16:19
 * @Version 1.0
 */

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Resource
    StatictisService statictisService;

    @OperationLogAnnotation(operModul = "用户模块-用户列表",operType = "查询",operDesc = "查询所有用户")
    @CrossOrigin
    @GetMapping()
    public ApiResult get(HttpServletRequest request) {
        request.setAttribute("userCode","admin");
        return statictisService.find();
    }
}
