package com.example.demo.repository.impl;

import com.example.demo.domain.po.SaleItemPO;
import com.example.demo.domain.po.VehicleInformationPO;
import com.example.demo.repository.SaleItemRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/13 22:53
 * @Version 1.0
 */
@Repository
public class SaleItemRepositoryImpl {

    @Resource
    SaleItemRepository saleItemRepository;

    public Integer save(SaleItemPO saleItemPO){
        return saleItemRepository.save(saleItemPO);
    }

    public void remove(Long id){
        saleItemRepository.remove(id);
    }

    public void update(SaleItemPO saleItemPO){
        saleItemRepository.update(saleItemPO);
    }

    public SaleItemPO findSaleItemById(Long id) {
        return saleItemRepository.findSaleItemById(id);
    }

    public SaleItemPO find(Long vehicleId){
        return saleItemRepository.findSaleItemByVehicleId(vehicleId);
    }

}
