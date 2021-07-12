package com.example.demo.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class TryoutCompanyVO {

    private String companyName;
    private Integer validAccount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 格式化
    private Timestamp expirationTime;
    private String abbreviation; // 公司简称
    private String username;
    private String password;

}
