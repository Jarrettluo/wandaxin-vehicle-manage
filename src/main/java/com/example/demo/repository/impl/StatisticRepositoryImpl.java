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
    public Integer calTotalSales() {
        return statisticsRepository.calTotalSales();
    }

    @Override
    public Integer calTotalProfit() {
        return statisticsRepository.calTotalProfit();
    }

    @Override
    public Integer calTotalNotSold() {
        return statisticsRepository.calTotalNotSold();
    }

    @Override
    public Integer calTotalSold() {
        return statisticsRepository.calTotalSold();
    }
}
