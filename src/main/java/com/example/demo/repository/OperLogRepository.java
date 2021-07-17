package com.example.demo.repository;


import com.example.demo.domain.po.OperationLogPO;
import com.example.demo.domain.po.VehicleInformationPO;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface OperLogRepository {


    @Insert("<script>INSERT INTO `operation_log`(user_code," +
            "ip, type, description, model," +
            "operation_time, result, params, company_id) VALUES(" +
            "#{userCode}, #{ip}, #{type}, " +
            "#{description}, #{model}, #{operationTime}, #{result}, #{params}" +
            ", #{companyId})</script>")
    void save(OperationLogPO operationLogPO);

    // mysql 的动态语句
    @Select({
            "<script>",
            "SELECT * ",
            "FROM operation_log",
            "where",
            "DATE_SUB(CURDATE(), INTERVAL 7 DAY) &lt;= date(operation_time) ",
            "and company_id = #{companyId}",
            "<if test='ids.size() &lt; 0'>",
            "and user_code in ",
            "<foreach item='id' index='index' collection='ids' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</if>",
            "</script>"
    })
    List<OperationLogPO> list(@Param("companyId") Long companyId, @Param("ids") List<String> ids);




}
