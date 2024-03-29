package com.example.demo.controller;

import com.example.demo.annotation.UserLoginToken;
import com.example.demo.service.OperationLogService;
import com.example.utils.result.ApiResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/log")
public class LogController {

    @Resource
    OperationLogService operationLogService;

    @UserLoginToken
    @GetMapping()
    public ApiResult list(@RequestParam("companyId") Long companyId, @RequestParam("userId") String userList)
    {
        String[] user = userList.split(",");
        List<String> userNameList = Arrays.asList(user);
        return operationLogService.list(companyId, userNameList);
    }
}
