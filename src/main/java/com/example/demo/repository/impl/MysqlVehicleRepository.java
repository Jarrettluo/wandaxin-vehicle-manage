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

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("<script>INSERT INTO `vehicle_information`(vehicle_plate," +
            "vehicle_brand, registration_date, vehicle_color, purchase_date," +
            "purchase_price, vehicle_note, company_id) VALUES(" +
            "#{vehiclePlate}, #{vehicleBrand}, #{registrationDate}, " +
            "#{vehicleColor}, #{purchaseDate}, #{purchasePrice}, #{vehicleNote}, #{companyId}" +
            ")</script>")
    Long save(VehicleInformationPO vehicleInformationPO);

    @Delete("DELETE FROM `vehicle_information` WHERE id = #{id}")
    void remove(Long id);

    @Update("<script> UPDATE `vehicle_information` <set>" +
            "<if test='vehiclePlate!=null'>vehicle_plate = #{vehiclePlate}, </if>" +
            "<if test='vehicleBrand!=null'>vehicle_brand = #{vehicleBrand}, </if>" +
            "<if test='registrationDate!=null'>registration_date = #{registrationDate}, </if>" +
            "<if test='vehicleColor!=null'>vehicle_color = #{vehicleColor}, </if>" +
            "<if test='purchaseDate!=null'>purchase_date = #{purchaseDate}, </if>" +
            "<if test='purchasePrice!=null'>purchase_price = #{purchasePrice}, </if>" +
            "<if test='vehicleNote!=null'>vehicle_note = #{vehicleNote}, </if>" +
            "saleitem_id = #{saleitemId}," +
            "</set> WHERE id = #{id}</script>")
    void update(VehicleInformationPO vehicleInformationPO);

    @Select("SELECT * FROM `vehicle_information` WHERE id = #{id}")
    @Results({
            @Result(property = "saleItem", column = "id",
                    one = @One(select = "com.example.demo.repository.SaleItemRepository.findSaleItemByVehicleId")),
            @Result(property = "partners", column = "id",
                    many = @Many(select = "com.example.demo.repository.PartnerRepository.findPartnersByVehicleId")),
            @Result(property = "preparednesses", column = "id",
                    many = @Many(select = "com.example.demo.repository.PreparednessRepository.findPreparednessByVehicleId"))
    })
    @Result(property = "id", column = "id")
    VehicleInformationPO find(Long id);

    @Select("SELECT * FROM `vehicle_information`")
    @Results({
            @Result(property = "saleItem", column = "id",
                    one = @One(select = "com.example.demo.repository.SaleItemRepository.findSaleItemByVehicleId")),
            @Result(property = "partners", column = "id",
                    many = @Many(select = "com.example.demo.repository.PartnerRepository.findPartnersByVehicleId")),
            @Result(property = "preparednesses", column = "id",
                    many = @Many(select = "com.example.demo.repository.PreparednessRepository.findPreparednessByVehicleId"))
    })
    @Result(property = "id", column = "id")
    List<VehicleInformationPO> list();

    @Select("SELECT * FROM `vehicle_information` where `company_id` = #{companyId} and `saleitem_id` is null ORDER BY modify_time DESC")
    @Results({
            @Result(property = "preparednesses", column = "id",
                    many = @Many(select = "com.example.demo.repository.PreparednessRepository.findPreparednessByVehicleId"))
    })
    @Result(property = "id", column = "id")
    List<VehicleInformationPO> listUnsale(Long companyId);

    @Select("SELECT * FROM `vehicle_information` where `company_id` = #{companyId} and `saleitem_id` is not null ORDER BY modify_time DESC")
    @Results({
            @Result(property = "saleItem", column = "id",
                    one = @One(select = "com.example.demo.repository.SaleItemRepository.findSaleItemByVehicleId")),
    })
    @Result(property = "id", column = "id")
    List<VehicleInformationPO> listSaled(Long companyId);



    @Select("SELECT * FROM vehicle_information WHERE CONCAT(`vehicle_plate`,`vehicle_brand`,`vehicle_color`) like \"%\" #{vehiclePlate} \"%\" " +
            "and `company_id` = #{companyId} ORDER BY modify_time DESC")
    List<VehicleInformationPO> search(String vehiclePlate, Long companyId);

}