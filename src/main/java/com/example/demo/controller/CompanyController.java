package com.example.demo.controller;

import com.example.demo.service.CompanyService;
import com.example.demo.service.OperationLogService;
import com.example.utils.result.ApiResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Resource
    CompanyService companyService;

    @CrossOrigin
    @GetMapping("/list/")
    public ApiResult list(@RequestParam Long companyId) {
        return companyService.list();
    }

    @CrossOrigin
    @GetMapping("/")
    public ApiResult find(@RequestParam Long companyId) {
        return companyService.find(companyId);
    }


}
