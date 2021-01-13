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


    @Insert("<script>INSERT INTO `vehicle_information`(vehicle_number, vehicle_plate," +
            "vehicle_brand, registration_date, vehicle_color, purchase_date," +
            "purchase_price, vehicle_note, repair_state, sale_state) VALUES(" +
            "#{vehicleNumber}, #{vehiclePlate}, #{vehicleBrand}, #{registrationDate}, " +
            "#{vehicleColor}, #{purchaseDate}, #{purchasePrice}, #{vehicleNote}, #{repairState}, #{saleState}" +
            ")</script>")
    Integer save(SaleItemPO saleItemPO);

    @Delete("DELETE FROM `vehicle_information` WHERE id = #{id}")
    void remove(Long id);

    // TODO 这里需要修改！
    @Update("<script> UPDATE `vehicle_information` <set>" +
            "<if test='vehicle_number!=null'>vehicle_num = #{vehicleNumber}, </if>" +
            "</set></script>")
    void update(SaleItemPO saleItemPO);



}