package com.example.demo.repository;

import com.example.demo.domain.po.PartnerPO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface VehicleDescRepository {

    /**
     * 根据车辆id查询所有的合作伙伴
     */
    @Select("SELECT * FROM `vhicle_description` WHERE vehicle_id = #{vehicleId}")
    List<PartnerPO> findPartnersByVehicleId(Long vehicleId);


    /**
     * 批量写入合作伙伴的信息
     */
    @Insert("<script> INSERT INTO `partner` (name, price, vehicle_id) values " +
            " <foreach collection ='result' item= 'item' separator =',' > " +
            " (#{item.name}, #{item.price}, #{item.vehicleId} ) </foreach>" +
            " </script>")
    Integer save(@Param(value = "result") List<PartnerPO> result);

    /**
     * 删除修改合作伙伴的信息
     */
    @Delete("DELETE FROM `partner` WHERE vehicle_id = #{vehicleId}")
    void remove(Long vehicleId);


}
