package com.example.demo.domain.dto;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class OperationLogDTO {

    String userCode;
    String ip;
    String type;
    String description;
    String model;
    Timestamp operationTime;
    String result;

}
