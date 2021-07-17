package com.example.demo.repository.impl;

import com.example.demo.domain.po.OperationLogPO;
import com.example.demo.repository.OperLogRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class OperLogRepositoryImpl {

    @Resource
    OperLogRepository operLogRepository;

    public void save(OperationLogPO operationLogPO) {
        operLogRepository.save(operationLogPO);
    }

    public List<OperationLogPO> list(Long companyId, List<String> userNameList) {
        return operLogRepository.list(companyId, userNameList);
    }

}
