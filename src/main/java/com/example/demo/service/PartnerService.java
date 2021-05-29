package com.example.demo.service;

import com.example.demo.domain.dto.PartnerDTO;
import com.example.demo.domain.dto.VehicleInformationDTO;
import com.example.utils.result.ApiResult;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/13 14:47
 * @Version 1.0
 */

public interface PartnerService {

    ApiResult save(PartnerDTO[] partners) throws IllegalAccessException;

    ApiResult remove(Long vehicleId);

    ApiResult list(Long vehicleId);

}
