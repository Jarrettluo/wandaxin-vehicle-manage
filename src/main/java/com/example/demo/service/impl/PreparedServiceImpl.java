package com.example.demo.service.impl;

import com.example.demo.domain.dto.PreparednessDTO;
import com.example.demo.domain.po.PreparednessPO;
import com.example.demo.domain.po.VehicleInformationPO;
import com.example.demo.repository.SaleItemRepository;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.repository.impl.PreparednessRepositoryImpl;
import com.example.demo.service.PreparedService;
import com.example.utils.result.ApiResult;
import com.example.utils.result.CheckObject;
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
    public ApiResult save(PreparednessDTO[] preparednessDTOS) throws IllegalAccessException {
        for( PreparednessDTO preparednessDTO: preparednessDTOS){
            if(CheckObject.checkObjFieldIsNull(preparednessDTO)){
                return ApiResult.error(1201,"参数错误");
            }
        }
        ArrayList<PreparednessDTO> preparednessDTOArrayList = new ArrayList<>(Arrays.asList(preparednessDTOS));
        List<PreparednessPO> preparednessPOList= BeanUtil.mapperList(preparednessDTOArrayList, PreparednessPO.class);
        Integer id = preparednessRepositoryImpl.save(preparednessPOList);
        if(id!=null) return ApiResult.success(id);
        else return ApiResult.error(1202, "保存失败");
    }

    @Override
    public ApiResult remove(Long vehicleId) {
        if(vehicleId==null)return ApiResult.error(1201, "参数错误");
        // 如果更新车辆的整备信息，那么必须对删除掉销售信息
        VehicleInformationPO vehicleInformationPO = vehicleRepository.find(vehicleId);
        if(vehicleInformationPO.getSaleitemId() != null) {
            vehicleInformationPO.setSaleitemId(null);
            try {
                vehicleRepository.update(vehicleInformationPO);
            }catch (Exception err){
                return ApiResult.error(1203, err.toString());
            }
        }
        try {
            // 删除其销售信息
            saleItemRepository.removeByVehicleId(vehicleId);
            preparednessRepositoryImpl.remove(vehicleId);
        } catch (Exception err){
            return ApiResult.error(1203, err.toString());
        }
        return ApiResult.success();
    }

    @Override
    public ApiResult list(Long vehicleId) {
        if(vehicleId == null) return ApiResult.error(1201, "参数错误");
        List<PreparednessPO> preparednessPOS = preparednessRepositoryImpl.list(vehicleId);
        List<PreparednessDTO> preparednessDTOList = BeanUtil.mapperList(preparednessPOS, PreparednessDTO.class);
        return ApiResult.success(preparednessDTOList);
    }
}
