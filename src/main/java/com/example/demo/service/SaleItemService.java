package com.example.demo.service;

import com.example.demo.domain.dto.SaleItemDTO;
import com.example.demo.domain.dto.VehicleInformationDTO;
import com.example.utils.result.ApiResult;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/13 22:50
 * @Version 1.0
 */
public interface SaleItemService {

    ApiResult save(SaleItemDTO saleItemDTO);

    ApiResult remove(Long id);

    ApiResult update(SaleItemDTO saleItemDTO);

    ApiResult find(Long id);
}
