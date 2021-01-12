package com.example.demo.repository;

import com.example.demo.domain.po.SaleItemPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
}