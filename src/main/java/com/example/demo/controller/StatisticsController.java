package com.example.demo.controller;

import com.example.demo.service.StatictisService;
import com.example.utils.result.ApiResult;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    @GetMapping()
    public ApiResult get(){
        return statictisService.find();
    }
}
