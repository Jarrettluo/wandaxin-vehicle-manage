package com.example.demo.repository.impl;

import com.example.demo.domain.po.PartnerPO;
import com.example.demo.domain.po.PreparednessPO;
import com.example.demo.repository.PartnerRepository;
import com.example.demo.repository.PreparednessRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/13 22:28
 * @Version 1.0
 */
@Repository
public class PreparednessRepositoryImpl {

    @Resource
    PreparednessRepository preparednessRepository;

    public List<PreparednessPO> list(Long vehicleId) {
        return preparednessRepository.findPreparednessByVehicleId(vehicleId);
    }

    public Integer save(List<PreparednessPO> preparednessPOS) {
        return preparednessRepository.save(preparednessPOS);
    }

    public void remove(Long vehicleId) {
        preparednessRepository.remove(vehicleId);
    }

}
