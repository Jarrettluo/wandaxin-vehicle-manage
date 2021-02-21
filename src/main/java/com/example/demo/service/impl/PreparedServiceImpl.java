package com.example.demo.service.impl;

import com.example.demo.domain.dto.PreparednessDTO;
import com.example.demo.domain.po.PreparednessPO;
import com.example.demo.domain.po.VehicleInformationPO;
import com.example.demo.repository.SaleItemRepository;
import com.example.demo.repository.VehicleRepository;
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

    @Resource
    VehicleRepository vehicleRepository;

    @Resource
    SaleItemRepository saleItemRepository;

    @Override
    public ApiResult save(PreparednessDTO[] preparednessDTOS) {
        ArrayList<PreparednessDTO> preparednessDTOArrayList = new ArrayList<>(Arrays.asList(preparednessDTOS));
        List<PreparednessPO> preparednessPOList= BeanUtil.mapperList(preparednessDTOArrayList, PreparednessPO.class);
        Integer id = preparednessRepositoryImpl.save(preparednessPOList);
        return ApiResult.success(id);
    }

    @Override
    public ApiResult remove(Long vehicleId) {
        // 如果更新车辆的整备信息，那么必须对删除掉销售信息
        VehicleInformationPO vehicleInformationPO = vehicleRepository.find(vehicleId);
        System.out.println(vehicleInformationPO);
        if(vehicleInformationPO.getSaleitemId() != null) {
            vehicleInformationPO.setSaleitemId(null);
            vehicleRepository.update(vehicleInformationPO);
        }
        // 删除其销售信息
        saleItemRepository.removeByVehicleId(vehicleId);

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
