package com.example.demo.service;

import com.example.demo.domain.dto.OperationLogDTO;
import com.example.utils.result.ApiResult;

import java.util.List;

public interface OperationLogService {

    void save(OperationLogDTO operationLogDTO) throws IllegalAccessException;

    ApiResult list(Long companyId, List<String> userIdList);
}
