package com.example.demo.repository.impl;

import com.example.demo.repository.StatisticsRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/14 10:39
 * @Version 1.0
 */
@Repository
public class StatisticRepositoryImpl implements StatisticsRepository {

    @Resource
    StatisticsRepository statisticsRepository;

    @Override
    public Float calTotalSales(Long companyId) {
        return statisticsRepository.calTotalSales(companyId);
    }

    @Override
    public Float calTotalProfit(Long companyId) {
        return statisticsRepository.calTotalProfit(companyId);
    }

    @Override
    public Integer calTotalNotSold(Long companyId) {
        return statisticsRepository.calTotalNotSold(companyId);
    }

    @Override
    public Integer calTotalSold(Long companyId) {
        return statisticsRepository.calTotalSold(companyId);
    }

    @Override
    public Integer monthStat(Long year, Integer month) {
        return statisticsRepository.monthStat(year, month);
    };
}
