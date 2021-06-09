package com.example.demo.repository.impl;

import com.example.demo.domain.po.OperationLogPO;
import com.example.demo.repository.OperLogRepository;

import javax.annotation.Resource;
import java.util.List;

public class OperLogRepositoryImpl {

    @Resource
    OperLogRepository operLogRepository;

    void save(OperationLogPO operationLogPO) {
        operLogRepository.save(operationLogPO);
    }

    public List<OperationLogPO> list() {
        return operLogRepository.list();
    }

}
