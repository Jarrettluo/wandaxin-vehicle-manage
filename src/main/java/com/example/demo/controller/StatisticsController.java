package com.example.demo.controller;

import com.example.demo.aop.OperationLogAnnotation;
import com.example.demo.service.StatictisService;
import com.example.utils.result.ApiResult;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/13 16:19
 * @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Resource
    StatictisService statictisService;

    @OperationLogAnnotation(operModul = "数据统计",operType = "查询",operDesc = "年度数据")
    @GetMapping()
    public ApiResult get(@RequestParam("companyId") Long companyId) {
        return statictisService.find(companyId);
    }

    @GetMapping("/fullYearStat")
    public ApiResult getFullYearStat(@RequestParam("year") @Min(2019) @Max(2050) Long year){
        return statictisService.getFullYearStat(year);
    }
}
