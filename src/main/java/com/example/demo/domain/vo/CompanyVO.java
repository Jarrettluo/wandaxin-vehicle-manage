package com.example.demo.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class CompanyVO {

    private String companyName;
    private Integer validAccount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 格式化
    private Timestamp expirationTime;
    private Timestamp createTime;

}
