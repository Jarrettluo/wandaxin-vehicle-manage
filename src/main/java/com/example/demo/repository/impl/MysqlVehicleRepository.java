package com.example.demo.repository.impl;

import com.example.demo.domain.po.VehicleInformationPO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/12 14:46
 * @Version 1.0
 */
@Mapper
public interface MysqlVehicleRepository {

    @Insert("<script>INSERT INTO `vehicle_information`(vehicle_number, vehicle_plate," +
            "vehicle_brand, registration_date, vehicle_color, purchase_date," +
            "purchase_price, vehicle_note, repair_state, sale_state) VALUES(" +
            "#{vehicleNumber}, #{vehiclePlate}, #{vehicleBrand}, #{registrationDate}, " +
            "#{vehicleColor}, #{purchaseDate}, #{purchasePrice}, #{vehicleNote}, #{repairState}, #{saleState}" +
            ")</script>")
    Integer save(VehicleInformationPO vehicleInformationPO);

    @Delete("DELETE FROM `vehicle_information` WHERE id = #{id}")
    void remove(Long id);

    // TODO 这里需要修改！
    @Update("<script> UPDATE `vehicle_information` <set>" +
            "<if test='vehicle_number!=null'>vehicle_num = #{vehicleNumber}, </if>" +
            "</set></script>")
    void update(VehicleInformationPO vehicleInformationPO);

    @Select("SELECT * FROM `vehicle_information` WHERE id = #{id}")
    @Results({
            @Result(property = "saleItem", column = "saleitem_id",
                    one = @One(select = "com.example.demo.repository.SaleItemRepository.findSaleItemById")),
            @Result(property = "partners", column = "id",
                    many = @Many(select = "com.example.demo.repository.PartnerRepository.findPartnersByVehicleId")),
            @Result(property = "preparednesses", column = "id",
                    many = @Many(select = "com.example.demo.repository.PreparednessRepository.findPreparednessByVehicleId"))
    })
    @Result(property = "id", column = "id")
    VehicleInformationPO find(Long id);

    @Select("SELECT * FROM `vehicle_information`")
    List<VehicleInformationPO> list();

}