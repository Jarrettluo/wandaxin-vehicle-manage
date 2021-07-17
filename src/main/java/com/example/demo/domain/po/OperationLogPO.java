package com.example.demo.domain.po;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OperationLogPO {

    Long id;
    String userCode;
    String ip;
    String type;
    String description;
    String model;
    Timestamp operationTime;
    String result;
    String params; // 访问参数
    Long companyId; // 公司的主键
}
