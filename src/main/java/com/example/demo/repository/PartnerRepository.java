package com.example.demo.repository;

import com.example.demo.domain.po.PartnerPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/12 14:53
 * @Version 1.0
 */

@Mapper
public interface PartnerRepository {

    /**
     * 根据车辆id查询所有的合作伙伴
     */
    @Select("SELECT * FROM `partner` WHERE vehicle_id = #{vehicleId}")
    List<PartnerPO> findPartnersByVehicleId(Long vehicleId);

}