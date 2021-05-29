package com.example.demo.service;

import com.example.demo.domain.dto.PartnerDTO;
import com.example.demo.domain.dto.PreparednessDTO;
import com.example.utils.result.ApiResult;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/13 22:25
 * @Version 1.0
 */
public interface PreparedService {

    ApiResult save(PreparednessDTO[] preparednessDTOS) throws IllegalAccessException;

    ApiResult remove(Long vehicleId);

    ApiResult list(Long vehicleId);

}
