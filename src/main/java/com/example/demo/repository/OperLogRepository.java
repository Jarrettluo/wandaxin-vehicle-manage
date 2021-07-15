package com.example.demo.repository;


import com.example.demo.domain.po.OperationLogPO;
import com.example.demo.domain.po.VehicleInformationPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
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

//    @Select("{ <script>" +
//            "SELECT * FROM `operation_log` " +
//            "where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(operation_time) " +
//            "<if test='userIdList.length >0 and userIdList != null'>" +
//            "and user_code in <foreach item='item' index='index' array='userNameList' open='(' separator=',' close=')'> " +
//            "#{item} </foreach> <if> and company_id = #{companyId}" +
//            "</script>}")
//    List<OperationLogPO> list(Long companyId, @Param("userNameList") List<String> userNameList);


    @Select("{<script>" +
            "SELECT * FROM `operation_log` " +
            "where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(operation_time) " +
            "and user_code in <foreach item='item' index='index' collection='userNameList' open='(' separator=',' close=')'> " +
            "#{item} </foreach> and company_id = #{companyId}" +
            "</script>}")
    List<OperationLogPO> list(@Param("companyId") Long companyId, @Param("userNameList") List<String> userNameList);

}
