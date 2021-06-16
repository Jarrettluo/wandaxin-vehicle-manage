package com.example.demo.repository;


import com.example.demo.domain.po.OperationLogPO;
import com.example.demo.domain.po.VehicleInformationPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.List;

public interface OperLogRepository {


    @Insert("<script>INSERT INTO `operation_log`(user_code," +
            "ip, type, description, model," +
            "operation_time, result, params) VALUES(" +
            "#{userCode}, #{ip}, #{type}, " +
            "#{description}, #{model}, #{operationTime}, #{result}, #{params}" +
            ")</script>")
    void save(OperationLogPO operationLogPO);

    @Select("SELECT * FROM `operation_log` where DATE_SUB(CURDATE(), INTERVAL 30 DAY) <= date(operation_time)")
    List<OperationLogPO> list();

}
