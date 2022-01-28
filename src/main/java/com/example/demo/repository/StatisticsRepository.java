package com.example.demo.repository;

import com.example.demo.domain.po.SaleItemPO;
import com.example.demo.domain.po.VehicleInformationPO;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/14 10:22
 * @Version 1.0
 */
@Mapper
public interface StatisticsRepository {

    @Select("SELECT SUM(`sale_price`) FROM `sale_item` WHERE YEAR(created_time) = #{year} and company_id = #{companyId}")
    Float calTotalSales(Long companyId, Long year);

    @Select("SELECT SUM(`self_profit`) FROM `sale_item` WHERE YEAR(created_time) = #{year} and company_id = #{companyId}")
    Float calTotalProfit(Long companyId, Long year);

    @Select("SELECT COUNT(*) FROM `vehicle_information` where `saleitem_id` is null and company_id = #{companyId}")
    Integer calTotalNotSold(Long companyId);

    @Select("SELECT COUNT(*) FROM `vehicle_information` where `saleitem_id` is not null and company_id = #{companyId}")
    Integer calTotalSold(Long companyId);

    @Select("SELECT COUNT(*) FROM `sale_item` WHERE year(sale_date) = #{year} and month(sale_date) = #{month} ")
    Integer monthStat(Long year, Integer month);

    @Select("SELECT vehicle_id FROM `sale_item` WHERE `company_id` = #{companyId} and year(sale_date) = #{year} and month(sale_date) = #{month}")
    List<Integer> findVehicleIdByMonth(Long companyId, Long year, Integer month);

    @Select("SELECT id FROM `vehicle_information` WHERE `company_id` = #{companyId} and year(purchase_date) = #{year} and month(purchase_date) = #{month}")
    List<Integer> findUnsaledVehicleIdByMonth(Long companyId, Long year, Integer month);

}
