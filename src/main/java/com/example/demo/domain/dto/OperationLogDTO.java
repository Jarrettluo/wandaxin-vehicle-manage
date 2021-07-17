package com.example.demo.domain.dto;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class OperationLogDTO {

    String userCode; // 用户名
    String ip;  // 访问ip
    String type; // 类型
    String description; // 描述
    String model; // 模块
    Timestamp operationTime; // 操作时间
    String result; // 结果 200表示正常，其他非正常的
    String params; // 访问参数
    Long companyId; // 公司的主键

}
