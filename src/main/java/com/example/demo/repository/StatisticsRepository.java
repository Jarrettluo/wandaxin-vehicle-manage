package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/14 10:22
 * @Version 1.0
 */
@Mapper
public interface StatisticsRepository {

    @Select("SELECT SUM(`sale_price`) FROM `sale_item` WHERE YEAR(created_time) = YEAR(NOW())")
    Float calTotalSales();

    @Select("SELECT SUM(`self_profit`) FROM `sale_item` WHERE YEAR(created_time) = YEAR(NOW())")
    Float calTotalProfit();

    @Select("SELECT COUNT(*) FROM `vehicle_information` where `saleitem_id` is null")
    Integer calTotalNotSold();
    // select count(*) from table where 字段 = "";

    @Select("SELECT COUNT(*) FROM `vehicle_information` where `saleitem_id` is not null")
    Integer calTotalSold();
}
