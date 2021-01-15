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
    public ApiResult find() {
        StatisticsDTO statisticsDTO = new StatisticsDTO();
        statisticsDTO.setTotalSales(statisticRepositoryImpl.calTotalSales());
        statisticsDTO.setTotalProfit(statisticRepositoryImpl.calTotalProfit());
        statisticsDTO.setTotalNotSold(statisticRepositoryImpl.calTotalNotSold());
        statisticsDTO.setTotalSold(statisticRepositoryImpl.calTotalSold());
        return ApiResult.success(statisticsDTO);
    }

}
