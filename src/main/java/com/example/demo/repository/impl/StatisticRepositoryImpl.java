package com.example.demo.repository.impl;

import com.example.demo.repository.StatisticsRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

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
    public Float calTotalSales(Long companyId, Long year) {
        return statisticsRepository.calTotalSales(companyId, year);
    }

    @Override
    public Float calTotalProfit(Long companyId, Long year) {
        return statisticsRepository.calTotalProfit(companyId, year);
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
    }

    @Override
    public List<Integer> findVehicleIdByMonth(Long companyId, Long year, Integer month) {
        return statisticsRepository.findVehicleIdByMonth(companyId, year, month);
    }

    @Override
    public List<Integer> findUnsaledVehicleIdByMonth(Long companyId, Long year, Integer month) {
        return statisticsRepository.findUnsaledVehicleIdByMonth(companyId, year, month);
    }
}
