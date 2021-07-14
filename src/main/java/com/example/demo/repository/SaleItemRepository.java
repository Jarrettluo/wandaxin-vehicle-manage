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
    @Insert("<script>INSERT INTO `sale_item` (" +
            "sale_price, commission_rate, repair_price, partner_price," +
            "partner_profit, self_profit, sale_date, vehicle_id, clear_state," +
            "mortgage_rebate, insurance_refund, company_id ) VALUES (" +
            "#{salePrice}, #{commissionRate}, #{repairPrice}, #{partnerPrice}," +
            "#{partnerProfit}, #{selfProfit}, #{saleDate}, #{vehicleId}, #{clearState}," +
            "#{mortgageRebate}, #{insuranceRefund}, #{companyId})</script>")
    Integer save(SaleItemPO saleItemPO);

    @Delete("DELETE FROM `sale_item` WHERE id = #{id}")
    void remove(Long id);

    // TODO 这里需要修改！
    @Update("<script> UPDATE `sale_item` <set>" +
            "<if test='salePrice!=null'>sale_price = #{salePrice}, </if>" +
            "<if test='commissionRate!=null'>commission_rate = #{commissionRate}, </if>" +
            "<if test='repairPrice!=null'>repair_price = #{repairPrice}, </if>" +
            "<if test='partnerPrice!=null'>partner_price = #{partnerPrice}, </if>" +
            "<if test='partnerProfit!=null'>partner_profit = #{partnerProfit}, </if>" +
            "<if test='selfProfit!=null'>self_profit = #{selfProfit}, </if>" +
            "<if test='saleDate!=null'>sale_date = #{saleDate}, </if>" +
            "<if test='vehicleId!=null'>vehicle_id = #{vehicleId}, </if>" +
            "<if test='clearState!=null'>clear_state = #{clearState}, </if>" +
            "<if test='mortgageRebate!=null'>mortgage_rebate = #{mortgageRebate}, </if>" +
            "<if test='insuranceRefund!=null'>insurance_refund = #{insuranceRefund}, </if>" +
            "</set>WHERE id = #{id}</script>")
    void update(SaleItemPO saleItemPO);


    @Delete("DELETE FROM `sale_item` WHERE vehicle_id = #{vehicleId}")
    void removeByVehicleId(Long vehicleId);


}