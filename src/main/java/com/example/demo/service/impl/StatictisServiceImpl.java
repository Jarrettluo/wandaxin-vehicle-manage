package com.example.demo.service.impl;

import com.example.demo.domain.dto.StatisticsDTO;
import com.example.demo.domain.dto.VehicleInformationDTO;
import com.example.demo.domain.po.VehicleInformationPO;
import com.example.demo.repository.impl.StatisticRepositoryImpl;
import com.example.demo.service.StatictisService;
import com.example.utils.result.ApiResult;
import com.example.utils.result.bean.BeanUtil;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

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

    @Override
    public ApiResult getFullYearStat(Long year) {
        HashMap<String, ArrayList<Integer>> monthStat = new HashMap<>();
        ArrayList<Integer> monthList = new ArrayList();
        ArrayList<Integer> statList = new ArrayList();
        for(int i = 0 ; i < 12; i++){
            monthList.add(i+1);
            statList.add(statisticRepositoryImpl.monthStat(year, i+1));
        }
        monthStat.put("month", monthList);
        monthStat.put("stat", statList);
        return ApiResult.success(monthStat);
    }
}
