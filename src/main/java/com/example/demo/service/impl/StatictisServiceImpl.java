package com.example.demo.service.impl;

import com.example.demo.domain.dto.StatisticsDTO;
import com.example.demo.domain.po.*;
import com.example.demo.repository.impl.MysqlVehicleRepository;
import com.example.demo.repository.impl.StatisticRepositoryImpl;
import com.example.demo.service.StatictisService;
import com.example.utils.result.ApiResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/13 16:22
 * @Version 1.0
 */
@Service
public class StatictisServiceImpl implements StatictisService {

    @Resource
    StatisticRepositoryImpl statisticRepositoryImpl;

    @Resource
    MysqlVehicleRepository mysqlVehicleRepository;

    @Override
    public ApiResult find(Long companyId, Long year) {
        StatisticsDTO statisticsDTO = new StatisticsDTO();
        Float totalSales = statisticRepositoryImpl.calTotalSales(companyId, year);

        if(totalSales == null ) {
            totalSales = 0f;
        }
        totalSales = (float) Math.round( totalSales * 100 )/ 100;
        statisticsDTO.setTotalSales(totalSales);
        Float totalProfit = statisticRepositoryImpl.calTotalProfit(companyId, year);

        if(totalProfit == null) {
            totalProfit = 0f;
        }
        totalProfit = (float) Math.round(totalProfit* 100 )/ 100;
        statisticsDTO.setTotalProfit(totalProfit);

        statisticsDTO.setTotalNotSold(statisticRepositoryImpl.calTotalNotSold(companyId));
        statisticsDTO.setTotalSold(statisticRepositoryImpl.calTotalSold(companyId));
        return ApiResult.success(statisticsDTO);
    }

    @Override
    public ApiResult getFullYearStat(Long year) {
        HashMap<String, ArrayList<Integer>> monthStat = new HashMap<>();
        ArrayList<Integer> monthList = new ArrayList<>();
        ArrayList<Integer> statList = new ArrayList<>();
        for(int i = 0 ; i < 12; i++){
            monthList.add(i+1);
            statList.add(statisticRepositoryImpl.monthStat(year, i+1));
        }
        monthStat.put("month", monthList);
        monthStat.put("stat", statList);
        return ApiResult.success(monthStat);
    }

    @Override
    public ApiResult getMonthStat(Long companyId, Long year, Integer month) {
        List<Integer> saledVehicleIdList = statisticRepositoryImpl.findVehicleIdByMonth(companyId, year, month);
        List<VehicleInformationPO> vehicleInformationPOS = new ArrayList<>();
        HashMap<String, Float> abstractInfo = new HashMap<>();
        HashMap<String, Object> resulteHashMap = new HashMap<>();
        // 统计总利润
        Float totalProfitNum = 0f;
        // 统计毛收入
        Float totalIncome = 0f;
        for(Integer vehicleId : saledVehicleIdList){
            Long vehicleIdLong = Long.valueOf(vehicleId);
            VehicleInformationPO vehicleInformationPO = mysqlVehicleRepository.find(vehicleIdLong);
            SaleItemPO saleItemPO = vehicleInformationPO.getSaleItem();
            totalProfitNum += saleItemPO.getSelfProfit();
            totalIncome += saleItemPO.getInsuranceRefund() + saleItemPO.getSalePrice() + saleItemPO.getMortgageRebate();
            vehicleInformationPOS.add(vehicleInformationPO);
        }
        abstractInfo.put("totalSaledNum", (float) vehicleInformationPOS.size());
        abstractInfo.put("totalProfitNum", totalProfitNum);
        abstractInfo.put("totalIncome", totalIncome);

        resulteHashMap.put("vehiceInfo", vehicleInformationPOS);
        resulteHashMap.put("abstractInfo", abstractInfo);

        return ApiResult.success(resulteHashMap);
    }

    @Override
    public ApiResult getMonthStatUnsaled(Long companyId, Long year, Integer month) {
        List<Integer> vehicleList = statisticRepositoryImpl.findUnsaledVehicleIdByMonth(companyId, year, month);
        List<VehicleInformationPO> vehicles = new ArrayList<>();
        HashMap<String, Float> abstractInfo = new HashMap<>();
        HashMap<String, Object> resulteHashMap = new HashMap<>();
        // 统计总支出
        Float totalOutcome = 0f;
        for(Integer vehicleId : vehicleList){
            Long vehicleIdLong = Long.valueOf(vehicleId);
            VehicleInformationPO vehicleInformationPO = mysqlVehicleRepository.find(vehicleIdLong);
            totalOutcome += vehicleInformationPO.getPurchasePrice();
            for(PreparednessPO preparednessPO : vehicleInformationPO.getPreparednesses()){
                totalOutcome += preparednessPO.getRepairPrice();
            }
            vehicles.add(vehicleInformationPO);
        }
        abstractInfo.put("totalUnsaledNum", (float) vehicles.size());
        abstractInfo.put("totalOutcome", totalOutcome);

        resulteHashMap.put("vehiceInfo", vehicles);
        resulteHashMap.put("abstractInfo", abstractInfo);

        return ApiResult.success(resulteHashMap);
    }
}
