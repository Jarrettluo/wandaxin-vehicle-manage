package com.example.demo.controller;

import com.example.demo.service.OperationLogService;
import com.example.utils.result.ApiResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/log")
public class LogController {

    @Resource
    OperationLogService operationLogService;

    @CrossOrigin
    @GetMapping()
    public ApiResult list(@RequestParam("companyId") Long companyId, @RequestParam("userId") String userList)
    {
        String str[] = userList.split(",");
        List<String> userNameList = Arrays.asList(str);
        return operationLogService.list(companyId, userNameList);
    }
}
