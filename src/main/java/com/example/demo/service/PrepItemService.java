package com.example.demo.service;

import com.example.demo.domain.dto.PreparatoryItemDTO;
import com.example.utils.result.ApiResult;


public interface PrepItemService {

    ApiResult addItem(PreparatoryItemDTO preparatoryItemDTO);
    ApiResult removeItem(Long id);
    ApiResult updateItem(PreparatoryItemDTO preparatoryItemDTO);
    ApiResult list(Long companyId);
}
