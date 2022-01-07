package com.example.demo.repository;


import com.example.demo.domain.po.VehicleDescriptionPO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VehicleDescRepository {

    /**
     * 根据车辆的id查询车辆的更多描述信息
     * @param vehicleId  车辆的主键id
     * @return 返回车辆的列表信息
     */
    @Select("SELECT * FROM `vehicle_description` WHERE vehicle_id = #{vehicleId}")
    List<VehicleDescriptionPO> findDescriptionByVehicleId(Long vehicleId);


    /**
     * 插入车辆的描述信息，例如车辆的vin
     * @param vehicleDescriptionPO 车辆描述信息
     * @return 返回车辆的保存结果
     */
    @Insert("<script> INSERT INTO `vehicle_description` (vehicle_id, vin_code) values " +
            "(#{vehicleDescriptionPO.vehicleId}, #{vehicleDescriptionPO.vinCode})" +
            " </script>")
    Integer save(@Param(value = "vehicleDescriptionPO") VehicleDescriptionPO vehicleDescriptionPO);

    /**
     * 删除某辆车的描述信息
     * @param vehicleId 车辆的主键id
     */
    @Delete("DELETE FROM `vehicle_description` WHERE vehicle_id = #{vehicleId}")
    void remove(Long vehicleId);

    /**
     * 更新车辆的描述信息
     * @param vehicleDescriptionPO 车辆的描述信息
     */
    @Update("<script> UPDATE `vehicle_description` <set>" +
            "vin_code = #{vinCode} " +
            "</set>WHERE vehicle_id = #{vehicleId}</script>")
    void update(VehicleDescriptionPO vehicleDescriptionPO);


}
