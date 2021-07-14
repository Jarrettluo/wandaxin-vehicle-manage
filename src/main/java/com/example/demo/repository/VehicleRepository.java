package com.example.demo.repository;

import com.example.demo.domain.po.VehicleInformationPO;
import com.example.demo.repository.impl.MysqlVehicleRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/12 14:44
 * @Version 1.0
 */

@Repository
public class VehicleRepository {

    @Resource
    MysqlVehicleRepository mysqlVehicleRepository;

    public Long save(VehicleInformationPO vehicleInformationPO){
        return mysqlVehicleRepository.save(vehicleInformationPO);
    }

    public void remove(Long id){
        mysqlVehicleRepository.remove(id);
    }

    public void update(VehicleInformationPO vehicleInformationPO){
        mysqlVehicleRepository.update(vehicleInformationPO);
    }

    public VehicleInformationPO find(Long id){
        return mysqlVehicleRepository.find(id);
    }

    public List<VehicleInformationPO> list() {
        return mysqlVehicleRepository.list();
    }

    public List<VehicleInformationPO> listSaled(Long companyId) {return mysqlVehicleRepository.listSaled(companyId);}

    public List<VehicleInformationPO> listUnsale(Long companyId) {return mysqlVehicleRepository.listUnsale(companyId);}

    public List<VehicleInformationPO> search(String vehiclePlate, Long companyId) {
        return mysqlVehicleRepository.search(vehiclePlate, companyId);
    }
}
