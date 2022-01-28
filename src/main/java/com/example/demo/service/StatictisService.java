package com.example.demo.service;

import com.example.utils.result.ApiResult;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/13 16:21
 * @Version 1.0
 */


public interface StatictisService {

    ApiResult find(Long companyId);

    ApiResult getFullYearStat(Long year);

    ApiResult getMonthStat(Long companyId, Long year, Integer month);

    ApiResult getMonthStatUnsaled(Long companyId, Long year, Integer month);


}
