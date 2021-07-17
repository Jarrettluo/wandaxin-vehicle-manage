package com.example.demo.service.impl;

import com.example.demo.domain.dto.StatisticsDTO;
import com.example.demo.domain.dto.VehicleInformationDTO;
import com.example.demo.domain.po.VehicleInformationPO;
import com.example.demo.repository.impl.StatisticRepositoryImpl;
import com.example.demo.service.StatictisService;
import com.example.utils.result.ApiResult;
import com.example.utils.result.bean.BeanUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/13 16:22
 * @Version 1.0
 */
@Service
public class StatictisServiceImpl implements StatictisService {

    @Resource
    StatisticRepositoryImpl statisticRepositoryImpl;

    @Override
    public ApiResult find(Long companyId) {
        StatisticsDTO statisticsDTO = new StatisticsDTO();
        Float totalSales = statisticRepositoryImpl.calTotalSales(companyId);

        if(totalSales == null ) totalSales = 0f;
        totalSales = (float) Math.round( totalSales * 100 )/ 100;
        statisticsDTO.setTotalSales(totalSales);
        Float totalProfit = statisticRepositoryImpl.calTotalProfit(companyId);

        if(totalProfit == null) totalProfit = 0f;
        totalProfit = (float) Math.round(totalProfit* 100 )/ 100;
        statisticsDTO.setTotalProfit(totalProfit);

        statisticsDTO.setTotalNotSold(statisticRepositoryImpl.calTotalNotSold(companyId));
        statisticsDTO.setTotalSold(statisticRepositoryImpl.calTotalSold(companyId));
        return ApiResult.success(statisticsDTO);
    }

}
