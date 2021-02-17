package com.example.demo.service;

import com.example.demo.domain.po.VehicleInformationPO;
import com.example.demo.repository.SaleItemRepository;
import com.example.demo.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author Jarrett Luo
 * @Date 2021/2/1 21:56
 * @Version 1.0
 */
@Service
public class UpdateSaleItem {
    @Resource
    VehicleRepository vehicleRepository;

    @Resource
    SaleItemRepository saleItemRepository;

    public void updateVehicleSale(Long id) {
        System.out.println("===========================");
        System.out.println(id);

    }
}