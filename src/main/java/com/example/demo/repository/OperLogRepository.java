package com.example.demo.repository;


import com.example.demo.domain.po.OperationLogPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import java.sql.Timestamp;

public interface OperLogRepository {


    @Insert("<script>INSERT INTO `operation_log`(user_code," +
            "ip, type, description, model," +
            "operation_time, result, params) VALUES(" +
            "#{userCode}, #{ip}, #{type}, " +
            "#{description}, #{model}, #{operationTime}, #{result}, #{params}" +
            ")</script>")
    void save(OperationLogPO operationLogPO);

}
