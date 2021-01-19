package com.example.demo.service.impl;

import com.example.demo.domain.dto.VehicleInformationDTO;
import com.example.demo.domain.po.VehicleInformationPO;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.service.VehicleService;
import com.example.utils.result.ApiResult;
import com.example.utils.result.bean.BeanUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/12 15:11
 * @Version 1.0
 */

@Service
public class VehicleServiceImpl implements VehicleService {

    @Resource
    VehicleRepository vehicleRepository;


    @Override
    public ApiResult save(VehicleInformationDTO vehicleInformationDTO) {
        VehicleInformationPO vehicleInformationPO = BeanUtil.mapperBean(vehicleInformationDTO, VehicleInformationPO.class);
        vehicleRepository.save(vehicleInformationPO);
        return ApiResult.success(vehicleInformationPO.getId());
    }

    @Override
    public ApiResult remove(Long id) {
        vehicleRepository.remove(id);
        return ApiResult.success();
    }

    @Override
    public ApiResult update(VehicleInformationDTO vehicleInformationDTO) {
        VehicleInformationPO vehicleInformationPO = BeanUtil.mapperBean(vehicleInformationDTO, VehicleInformationPO.class);
        vehicleRepository.update(vehicleInformationPO);
        return ApiResult.success();
    }

    @Override
    public ApiResult find(Long id) {
        VehicleInformationPO vehicleInformationPO = vehicleRepository.find(id);
        VehicleInformationDTO vehicleInformationDTO = BeanUtil.mapperBean(vehicleInformationPO, VehicleInformationDTO.class);
        return ApiResult.success(vehicleInformationDTO);
    }

    @Override
    public ApiResult list() {
        List<VehicleInformationPO> vehicleInformationPOList = vehicleRepository.list();
        List<VehicleInformationDTO> vehicleInformationDTOList = BeanUtil.mapperList(vehicleInformationPOList, VehicleInformationDTO.class);
        return ApiResult.success(vehicleInformationDTOList);
    }
    
    @Override
    public ApiResult search(String vehiclePlate) {
        List<VehicleInformationPO> vehicleInformationPOList = vehicleRepository.search(vehiclePlate);
        List<VehicleInformationDTO> vehicleInformationDTOList = BeanUtil.mapperList(vehicleInformationPOList, VehicleInformationDTO.class);
        return ApiResult.success(vehicleInformationDTOList);
    }
}
