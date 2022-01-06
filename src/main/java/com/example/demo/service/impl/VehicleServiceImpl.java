package com.example.demo.service.impl;

import com.example.demo.domain.dto.VehicleInformationDTO;
import com.example.demo.domain.po.VehicleDescriptionPO;
import com.example.demo.domain.po.VehicleInformationPO;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.repository.impl.PreparednessRepositoryImpl;
import com.example.demo.repository.impl.SaleItemRepositoryImpl;
import com.example.demo.service.VehicleService;
import com.example.utils.result.ApiResult;
import com.example.utils.result.bean.BeanUtil;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Resource
    PartnerServiceImpl partnerServiceImpl;

    @Resource
    PreparednessRepositoryImpl preparednessRepositoryImpl;

    @Resource
    SaleItemRepositoryImpl saleItemRepositoryImpl;

    @Override
    public ApiResult save(VehicleInformationDTO vehicleInformationDTO) {
        if(vehicleInformationDTO.getVehicleBrand()==null || vehicleInformationDTO.getPurchasePrice()==null){
            return ApiResult.error(1201,"参数错误");
        }
        VehicleInformationPO vehicleInformationPO = BeanUtil.mapperBean(vehicleInformationDTO, VehicleInformationPO.class);
        vehicleRepository.save(vehicleInformationPO);
        if(vehicleInformationPO.getId() == null || "".equals(vehicleInformationPO.getId())){
            return ApiResult.error(1203, "保存失败!");
        }
        // 用于保存车辆的vin码
        String vinCode = vehicleInformationDTO.getVinCode();
        if(!"".equals(vinCode) && vinCode != null){
            VehicleDescriptionPO vehicleDescriptionPO = new VehicleDescriptionPO();
            vehicleDescriptionPO.setVehicleId(vehicleInformationPO.getId());
            vehicleDescriptionPO.setVinCode(vinCode);



        }

        return ApiResult.success(vehicleInformationPO.getId());
    }

    /**
     * 删除某辆车的信息的时候，应该同步删除其销售信息，如果存在的话！
     * @param id 车辆的id
     * @return 返回成功信息
     */
    @Override
    public ApiResult remove(Long id) {
        if(id == null ){
            return ApiResult.error(1201, "参数错误");
        }
        VehicleInformationPO vehicleInformationPO = vehicleRepository.find(id);
        // 如果能够查询到该辆车的话就把该辆车的销售信息给删除
        if(vehicleInformationPO.getSaleitemId()!=null) {
            saleItemRepositoryImpl.remove(vehicleInformationPO.getSaleitemId());
        }
        try {
            // 删除车辆的时候必须删除车辆的整备信息和合资人信息
            partnerServiceImpl.remove(id);
            preparednessRepositoryImpl.remove(id);
            vehicleRepository.remove(id);
        }catch (Exception err){
            return ApiResult.error(1202, "删除失败"+err);
        }
        return ApiResult.success();
    }

    @Override
    public ApiResult update(VehicleInformationDTO vehicleInformationDTO) {
        if(vehicleInformationDTO.getId() == null || vehicleInformationDTO.getVehicleBrand()==null || vehicleInformationDTO.getPurchasePrice() == null) {
            return ApiResult.error(1201, "参数错误");
        }
        VehicleInformationPO vehicleInformationPO = BeanUtil.mapperBean(vehicleInformationDTO, VehicleInformationPO.class);
        vehicleRepository.update(vehicleInformationPO);
        return ApiResult.success();
    }

    @Override
    public ApiResult find(Long id) {
        VehicleInformationPO vehicleInformationPO = vehicleRepository.find(id);
        VehicleInformationDTO vehicleInformationDTO = BeanUtil.mapperBean(vehicleInformationPO, VehicleInformationDTO.class);
        if(vehicleInformationDTO.getId() != null ) {
            return ApiResult.success(vehicleInformationDTO);
        }else {
            return ApiResult.error(1202, "查找失败");
        }
    }

    @Override
    public ApiResult list(Long companyId, String sellState) {
        if(StringUtils.isEmpty(companyId) || StringUtils.isEmpty(sellState)) return ApiResult.error(1201, "参数不足");
        List<VehicleInformationPO> vehicleInformationPOList = new ArrayList<>();
        if("unsale".equals(sellState)){
            vehicleInformationPOList = vehicleRepository.listUnsale(companyId);
        }else {
            vehicleInformationPOList = vehicleRepository.listSaled(companyId);
        }
        List<VehicleInformationDTO> vehicleInformationDTOList = BeanUtil.mapperList(vehicleInformationPOList, VehicleInformationDTO.class);
        if (vehicleInformationDTOList.size() > 0) {
            return ApiResult.success(vehicleInformationDTOList);
        } else {
            return ApiResult.error(1202,"车辆列表为空");
        }
    }
    
    @Override
    public ApiResult search(String vehiclePlate, Long companyId) {
        if(vehiclePlate == null || "".equals(companyId)) return ApiResult.error(1201, "参数不足");
        List<VehicleInformationPO> vehicleInformationPOList = vehicleRepository.search(vehiclePlate, companyId);
        List<VehicleInformationDTO> vehicleInformationDTOList = BeanUtil.mapperList(vehicleInformationPOList, VehicleInformationDTO.class);
        if (vehicleInformationDTOList.size() > 0) {
            return ApiResult.success(vehicleInformationDTOList);
        } else {
            return ApiResult.error(1202, "查询失败！");
        }
    }
}
