package com.example.demo.domain.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CompanyDTO {

    private String companyName; // 公司全称
    private String abbreviation; // 公司简称
    private Integer validAccount; // 有效账户数量
    private Timestamp expiration; // 公司失效时间

}
