package com.example.demo.controller;

import com.example.demo.aop.OperationLogAnnotation;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.service.StatictisService;
import com.example.utils.result.ApiResult;
import io.swagger.annotations.Api;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

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

    @Resource
    CompanyRepository companyRepository;

    @OperationLogAnnotation(operModul = "数据统计",operType = "查询",operDesc = "年度数据")
    @GetMapping()
    public ApiResult get(@RequestParam("companyId") Long companyId,
                         @RequestParam("year") Long year) {
        return statictisService.find(companyId, year);
    }

    /**
     * 某年的各月统计售出总数
     * @param year
     * @return
     */
    @GetMapping("/fullYearStat")
    public ApiResult getFullYearStat(@RequestParam("year") @Min(2019) @Max(2050) Long year,
                                     @RequestParam("companyId") @NotBlank Long companyId){
        // 校验该公司的id是否正常存在的
        if(companyRepository.find(companyId)==null){
            return ApiResult.error(1201, "公司不存在！");
        }
        return statictisService.getFullYearStat(year);
    }

    @GetMapping("/monthSaledStatDetail")
    public ApiResult monthSaledStatDetail(@RequestParam("companyId") @NotBlank Long companyId,
                                  @RequestParam("year") @Range(min = 2019,max =2050, message = "年龄取值范围1-50") Long year,
                                  @RequestParam("month") @Range(min = 1,max =12, message = "年龄取值范围1-50") Integer month
                                  ){
        // 校验该公司的id是否正常存在的
        if(companyRepository.find(companyId)==null){
            return ApiResult.error(1201, "公司不存在！");
        }
        return statictisService.getMonthStat(companyId, year, month);
    }

    @GetMapping("/monthUnsaledStatDetail")
    public ApiResult monthUnsaledStatDetail(@RequestParam("companyId") @NotBlank Long companyId,
                                  @RequestParam("year") @Range(min = 2019,max =2050, message = "年龄取值范围1-50") Long year,
                                  @RequestParam("month") @Range(min = 1,max =12, message = "年龄取值范围1-50") Integer month
    ){
        // 校验该公司的id是否正常存在的
        if(companyRepository.find(companyId)==null){
            return ApiResult.error(1201, "公司不存在！");
        }
        return statictisService.getMonthStatUnsaled(companyId, year, month);
    }

}
