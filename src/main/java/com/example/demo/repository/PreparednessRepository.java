package com.example.demo.repository;

import com.example.demo.domain.po.PartnerPO;
import com.example.demo.domain.po.PreparednessPO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/12 15:06
 * @Version 1.0
 */

@Mapper
public interface PreparednessRepository {

    /**
     * 根据车辆id查询所有的合作伙伴
     */
    @Select("SELECT * FROM `preparedness` WHERE vehicle_id = #{vehicleId}")
    List<PreparednessPO> findPreparednessByVehicleId(Long vehicleId);

    /**
     * 批量写入合作伙伴的信息
     */
    @Insert("<script> INSERT INTO `preparedness` (repair_item, repair_price, handler_name, handle_date," +
            " vehicle_id) values " +
            " <foreach collection ='result' item= 'item' separator =',' > " +
            " (#{item.repairItem}, #{item.repairPrice}, #{item.handlerName}, #{item.handleDate}," +
            " #{item.vehicleId} ) </foreach>" +
            " </script>")
    Integer save(@Param(value = "result") List<PreparednessPO> result);


    /**
     * 删除修改合作伙伴的信息
     */
    @Delete("DELETE FROM `preparedness` WHERE vehicle_id = #{vehicleId}")
    void remove(Long vehicleId);

}
