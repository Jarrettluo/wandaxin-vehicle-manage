package com.example.demo.service.impl;

import com.example.demo.domain.dto.OperationLogDTO;
import com.example.demo.domain.po.OperationLogPO;
import com.example.utils.result.ApiResult;
import org.springframework.stereotype.Service;

@Service
public class OperationLogService implements com.example.demo.service.OperationLogService {

    @Override
    public void save(OperationLogDTO operationLogDTO) {
        System.out.println(operationLogDTO);
    }

    @Override
    public ApiResult List() {
        return null;
    }
}
