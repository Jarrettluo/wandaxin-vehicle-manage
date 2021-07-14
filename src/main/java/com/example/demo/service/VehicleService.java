package com.example.demo.service;

import com.example.demo.domain.dto.VehicleInformationDTO;
import com.example.utils.result.ApiResult;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/12 15:08
 * @Version 1.0
 */

public interface VehicleService {

    ApiResult save(VehicleInformationDTO vehicleInformationDTO);

    ApiResult remove(Long id);

    ApiResult update(VehicleInformationDTO vehicleInformationDTO);

    ApiResult find(Long id);

    ApiResult list(Long companyId, String sellState);
    
    ApiResult search(String vehiclePlate, Long companyId);
}
