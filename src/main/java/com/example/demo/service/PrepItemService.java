package com.example.demo.service;

import com.example.demo.domain.dto.PreparatoryItemDTO;
import com.example.utils.result.ApiResult;

public interface PrepItemService {

    ApiResult addItem(PreparatoryItemDTO PreparatoryItemDTO);
    ApiResult removeItem(Long id);
    ApiResult updateItem(PreparatoryItemDTO PreparatoryItemDTO);
    ApiResult list(Long companyId);
}
