package com.example.demo.service.impl;

import com.example.demo.domain.dto.PartnerDTO;
import com.example.demo.domain.po.PartnerPO;
import com.example.demo.repository.impl.PartnerRepositoryImpl;
import com.example.demo.service.PartnerService;
import com.example.utils.result.ApiResult;
import com.example.utils.result.bean.BeanUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/13 14:48
 * @Version 1.0
 */
@Service
public class PartnerServiceImpl implements PartnerService {

    @Resource
    PartnerRepositoryImpl partnerRepositoryImpl;

    @Override
    public ApiResult save(PartnerDTO[] partners) {
        ArrayList<PartnerDTO> partnerDTOList = new ArrayList<>(Arrays.asList(partners));
        List<PartnerPO> partnerPOList= BeanUtil.mapperList(partnerDTOList, PartnerPO.class);
        partnerRepositoryImpl.save(partnerPOList);
        return ApiResult.success();
    }

    @Override
    public ApiResult list(Long vehicleId) {
        List<PartnerPO> partnerPOList = partnerRepositoryImpl.list(vehicleId);
        List<PartnerDTO> partnerDTOList = BeanUtil.mapperList(partnerPOList, PartnerDTO.class);
        return ApiResult.success(partnerDTOList);
    }

    @Override
    public ApiResult remove(Long vehicleId) {
        partnerRepositoryImpl.remove(vehicleId);
        return ApiResult.success();
    }

}
