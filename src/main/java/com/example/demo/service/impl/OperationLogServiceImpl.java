package com.example.demo.service.impl;

import com.example.demo.domain.dto.OperationLogDTO;
import com.example.demo.domain.po.OperationLogPO;
import com.example.demo.repository.OperLogRepository;
import com.example.demo.service.OperationLogService;
import com.example.utils.result.ApiResult;
import com.example.utils.result.bean.BeanUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OperationLogServiceImpl implements OperationLogService {

    @Resource
    OperLogRepository operLogRepository;

    @Override
    public void save(OperationLogDTO operationLogDTO) {
        OperationLogPO operationLogPO = BeanUtil.mapperBean(operationLogDTO, OperationLogPO.class);
        operLogRepository.save(operationLogPO);
    }

    @Override
    public ApiResult list() {
        List<OperationLogPO> operationLogPOList = operLogRepository.list();
        List<OperationLogDTO> operationLogDTOList = BeanUtil.mapperList(operationLogPOList, OperationLogDTO.class);
        return ApiResult.success(operationLogDTOList);
    }
}
