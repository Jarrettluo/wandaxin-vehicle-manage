package com.example.demo.service.impl;

import com.example.demo.domain.dto.PartnerDTO;
import com.example.demo.domain.dto.PreparednessDTO;
import com.example.demo.domain.po.PartnerPO;
import com.example.demo.domain.po.PreparednessPO;
import com.example.demo.repository.impl.PreparednessRepositoryImpl;
import com.example.demo.service.PreparedService;
import com.example.utils.result.ApiResult;
import com.example.utils.result.bean.BeanUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/13 22:25
 * @Version 1.0
 */
@Service
public class PreparedServiceImpl implements PreparedService {

    @Resource
    PreparednessRepositoryImpl preparednessRepositoryImpl;

    @Override
    public ApiResult save(PreparednessDTO[] preparednessDTOS) {
        ArrayList<PreparednessDTO> preparednessDTOArrayList = new ArrayList<>(Arrays.asList(preparednessDTOS));
        List<PreparednessPO> preparednessPOList= BeanUtil.mapperList(preparednessDTOArrayList, PreparednessPO.class);
        preparednessRepositoryImpl.save(preparednessPOList);
        return ApiResult.success();
    }

    @Override
    public ApiResult remove(Long vehicleId) {
        preparednessRepositoryImpl.remove(vehicleId);
        return ApiResult.success();
    }

    @Override
    public ApiResult list(Long vehicleId) {
        List<PreparednessPO> preparednessPOS = preparednessRepositoryImpl.list(vehicleId);
        List<PreparednessDTO> preparednessDTOList = BeanUtil.mapperList(preparednessPOS, PreparednessDTO.class);
        return ApiResult.success(preparednessDTOList);
    }
}
