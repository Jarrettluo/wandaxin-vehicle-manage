package com.example.demo.service.impl;

import com.example.demo.domain.dto.OperationLogDTO;
import com.example.demo.domain.po.OperationLogPO;
import com.example.demo.repository.OperLogRepository;
import com.example.demo.repository.impl.OperLogRepositoryImpl;
import com.example.demo.service.OperationLogService;
import com.example.utils.result.ApiResult;
import com.example.utils.result.CheckObject;
import com.example.utils.result.bean.BeanUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OperationLogServiceImpl implements OperationLogService {

    @Resource
    OperLogRepositoryImpl operLogRepositoryImpl;

    @Override
    public void save(OperationLogDTO operationLogDTO) throws IllegalAccessException {
        if(CheckObject.checkObjFieldIsNull(operationLogDTO)) return; // 如果参数为空直接跳过记录
        OperationLogPO operationLogPO = BeanUtil.mapperBean(operationLogDTO, OperationLogPO.class);
        operLogRepositoryImpl.save(operationLogPO);
    }

    @Override
    public ApiResult list(Long companyId, List<String> userIdList) {
        if(companyId.equals("")) return ApiResult.error(1201, "参数不足");
        List<OperationLogPO> operationLogPOList = operLogRepositoryImpl.list(companyId, userIdList);
        List<OperationLogDTO> operationLogDTOList = BeanUtil.mapperList(operationLogPOList, OperationLogDTO.class);
        return ApiResult.success(operationLogDTOList);
    }
}
