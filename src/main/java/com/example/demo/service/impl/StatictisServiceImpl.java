package com.example.demo.service.impl;

import com.example.demo.domain.dto.StatisticsDTO;
import com.example.demo.domain.dto.VehicleInformationDTO;
import com.example.demo.domain.po.VehicleInformationPO;
import com.example.demo.service.StatictisService;
import com.example.utils.result.ApiResult;
import com.example.utils.result.bean.BeanUtil;
import org.springframework.stereotype.Service;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/13 16:22
 * @Version 1.0
 */
@Service
public class StatictisServiceImpl implements StatictisService {

    @Override
    public ApiResult find() {
        // VehicleInformationPO vehicleInformationPO = /**/
        // StatisticsDTO statisticsDTO = BeanUtil.mapperBean(vehicleInformationDTO, VehicleInformationPO.class);


        StatisticsDTO statisticsDTO = new StatisticsDTO();
        return ApiResult.success(statisticsDTO);
    }

}
