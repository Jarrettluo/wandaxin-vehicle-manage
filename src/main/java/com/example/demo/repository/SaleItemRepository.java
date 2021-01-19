package com.example.demo.repository;

import com.example.demo.domain.po.SaleItemPO;
import com.example.demo.domain.po.VehicleInformationPO;
import org.apache.ibatis.annotations.*;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/12 14:52
 * @Version 1.0
 */

@Mapper
public interface SaleItemRepository {

    /**
     * 根据id查找
     */
    @Select("SELECT * FROM `sale_item` WHERE id = #{id}")
    SaleItemPO findSaleItemById(Long id);

    /**
     * 根据车辆id查找销售项目
     */
    @Select("SELECT * FROM `sale_item` WHERE vehicle_id = #{vehicleId} ")
    SaleItemPO findSaleItemByVehicleId(Long vehicleId);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("<script>INSERT INTO `sale_item`(" +
            "sale_price, commission_rate, repair_price, partner_price," +
            "partner_profit, self_profit, sale_date, vehicle_id) VALUES(" +
            "#{salePrice}, #{commissionRate}, #{repairPrice}, #{partnerPrice}, " +
            "#{partnerProfit}, #{selfProfit}, #{saleDate}, #{vehicleId}" +
            ")</script>")
    Integer save(SaleItemPO saleItemPO);

    @Delete("DELETE FROM `sale_item` WHERE id = #{id}")
    void remove(Long id);

    // TODO 这里需要修改！
    @Update("<script> UPDATE `sale_item` <set>" +
            "<if test='sale_price!=null'>sale_price = #{salePrice}, </if>" +
            "<if test='commission_rate!=null'>commission_rate = #{commissionRate}, </if>" +
            "<if test='repair_price!=null'>repair_price = #{repairPrice}, </if>" +
            "<if test='partner_price!=null'>partner_price = #{partnerPrice}, </if>" +
            "<if test='partner_profit!=null'>partner_profit = #{partnerProfit}, </if>" +
            "<if test='self_profit!=null'>self_profit = #{selfProfit}, </if>" +
            "<if test='sale_date!=null'>sale_date = #{saleDate}, </if>" +
            "<if test='vehicle_id!=null'>vehicle_id = #{vehicleId}, </if>" +
            "</set></script>")
    void update(SaleItemPO saleItemPO);



}