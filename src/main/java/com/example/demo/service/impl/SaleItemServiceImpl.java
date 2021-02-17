package com.example.demo.service.impl;

import com.example.demo.domain.dto.SaleItemDTO;
import com.example.demo.domain.dto.VehicleInformationDTO;
import com.example.demo.domain.po.SaleItemPO;
import com.example.demo.domain.po.VehicleInformationPO;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.repository.impl.SaleItemRepositoryImpl;
import com.example.demo.service.SaleItemService;
import com.example.utils.result.ApiResult;
import com.example.utils.result.bean.BeanUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/13 22:57
 * @Version 1.0
 */
@Service
public class SaleItemServiceImpl implements SaleItemService {

    @Resource
    SaleItemRepositoryImpl saleItemRepositoryImpl;

    @Resource
    VehicleRepository vehicleRepository;

    @Override
    public ApiResult save(SaleItemDTO saleItemDTO) {
        SaleItemPO saleItemPO = BeanUtil.mapperBean(saleItemDTO, SaleItemPO.class);
        saleItemRepositoryImpl.save(saleItemPO);

        VehicleInformationPO vehicleInformationPO = vehicleRepository.find(saleItemPO.getVehicleId());
        vehicleInformationPO.setSaleitemId(saleItemPO.getId());
        vehicleRepository.update(vehicleInformationPO);

        return ApiResult.success(saleItemPO.getId());
    }


    @Override
    public ApiResult remove(Long id) {
        SaleItemPO saleItemPO = saleItemRepositoryImpl.findSaleItemById(id);
        saleItemRepositoryImpl.remove(id);
        VehicleInformationPO vehicleInformationPO = vehicleRepository.find(saleItemPO.getVehicleId());
        vehicleInformationPO.setSaleitemId(null);
        vehicleRepository.update(vehicleInformationPO);
        return ApiResult.success();
    }

    @Override
    public ApiResult update(SaleItemDTO saleItemDTO) {
        SaleItemPO saleItemPO = BeanUtil.mapperBean(saleItemDTO, SaleItemPO.class);
        saleItemRepositoryImpl.update(saleItemPO);
        return ApiResult.success();
    }

    @Override
    public ApiResult find(Long vehicleId) {
        SaleItemPO saleItemPO = saleItemRepositoryImpl.find(vehicleId);
        SaleItemDTO saleItemDTO = BeanUtil.mapperBean(saleItemPO, SaleItemDTO.class);
        return ApiResult.success(saleItemDTO);
    }
}
