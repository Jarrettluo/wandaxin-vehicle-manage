package com.example.demo.service.impl;

import com.example.demo.domain.dto.SaleItemDTO;
import com.example.demo.domain.dto.VehicleInformationDTO;
import com.example.demo.domain.po.SaleItemPO;
import com.example.demo.domain.po.VehicleInformationPO;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.repository.impl.SaleItemRepositoryImpl;
import com.example.demo.service.SaleItemService;
import com.example.utils.result.ApiResult;
import com.example.utils.result.CheckObject;
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
    public ApiResult save(SaleItemDTO saleItemDTO) throws IllegalAccessException {
        if (CheckObject.checkObjFieldIsNull(saleItemDTO)) {
            return ApiResult.error(1201, "参数错误");
        }
        SaleItemPO saleItemPO = BeanUtil.mapperBean(saleItemDTO, SaleItemPO.class);
        saleItemRepositoryImpl.save(saleItemPO);
        // 查找车辆的id，有可能存在车辆被删除的情况
        VehicleInformationPO vehicleInformationPO = vehicleRepository.find(saleItemPO.getVehicleId());
        if(vehicleInformationPO.getId() == null) {
            return ApiResult.error(1202, "保存失败！");
        }
        vehicleInformationPO.setSaleitemId(saleItemPO.getId());
        vehicleRepository.update(vehicleInformationPO);
        return ApiResult.success(saleItemPO.getId());
    }


    @Override
    public ApiResult remove(Long id) {
        SaleItemPO saleItemPO = saleItemRepositoryImpl.findSaleItemById(id);
        if (saleItemPO.getId()==null){
            return ApiResult.error(1201,"参数错误");
        }
        VehicleInformationPO vehicleInformationPO = vehicleRepository.find(saleItemPO.getVehicleId());
        vehicleInformationPO.setSaleitemId(null);
        vehicleRepository.update(vehicleInformationPO); // 将车辆的销售id置为空值
        saleItemRepositoryImpl.remove(id); // 删除车辆的销售条目
        return ApiResult.success();
    }

    @Override
    public ApiResult update(SaleItemDTO saleItemDTO) throws IllegalAccessException {
        if(saleItemDTO.getId() == null) {
            return ApiResult.error(1201,"参数错误！");
        }
        if(CheckObject.checkObjFieldIsNull(saleItemDTO)){
            return ApiResult.error(1201,"参数错误！");
        }
        // 先去查找一下是否有这么一条记录，然后再进行修改
        SaleItemPO saleItemPOOld = saleItemRepositoryImpl.find(saleItemDTO.getVehicleId());
        System.out.println(saleItemPOOld);
        if(CheckObject.checkObjFieldIsNull(saleItemPOOld)) return ApiResult.error(1202, "修改失败");
        SaleItemPO saleItemPO = BeanUtil.mapperBean(saleItemDTO, SaleItemPO.class);
        System.out.println("--------");
        System.out.println(saleItemPO);
        saleItemRepositoryImpl.update(saleItemPO);
        return ApiResult.success(saleItemPO);
    }

    @Override
    public ApiResult find(Long vehicleId) {
        if (vehicleId==null) return ApiResult.error(1201, "参数错误");
        SaleItemPO saleItemPO = saleItemRepositoryImpl.find(vehicleId);
        SaleItemDTO saleItemDTO = BeanUtil.mapperBean(saleItemPO, SaleItemDTO.class);
        return ApiResult.success(saleItemDTO);
    }
}
