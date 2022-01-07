package com.example.demo.repository.impl;

import com.example.demo.domain.po.VehicleDescriptionPO;
import com.example.demo.repository.VehicleDescRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class VehicleDescRepositoryImpl {

    @Resource
    VehicleDescRepository vehicleDescRepository;

    public List<VehicleDescriptionPO> findDescriptionByVehicleId(Long vehicleId) {
        return vehicleDescRepository.findDescriptionByVehicleId(vehicleId);
    }

    public Integer save(VehicleDescriptionPO vehicleDescriptionPO) {
        return vehicleDescRepository.save(vehicleDescriptionPO);
    }

    public void remove(Long vehicleId) {
        vehicleDescRepository.remove(vehicleId);
    }


    public void update(VehicleDescriptionPO vehicleDescriptionPO) {
        vehicleDescRepository.update(vehicleDescriptionPO);
    }
}
