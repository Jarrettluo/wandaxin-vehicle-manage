package com.example.demo.repository.impl;

import com.example.demo.domain.po.PartnerPO;
import com.example.demo.domain.po.VehicleInformationPO;
import com.example.demo.repository.PartnerRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/13 14:55
 * @Version 1.0
 */
@Repository
public class PartnerRepositoryImpl {

    @Resource
    PartnerRepository partnerRepository;

    public List<PartnerPO> list(Long vehicleId) {
        return partnerRepository.findPartnersByVehicleId(vehicleId);
    }

    public Integer save(List<PartnerPO> partners) {
        return partnerRepository.save(partners);
    }

    public void remove(Long vehicleId) {
        partnerRepository.remove(vehicleId);
    }

}
