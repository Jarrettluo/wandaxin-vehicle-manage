package com.example.demo.domain.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.sql.Timestamp;

@Data
public class CompanyPO {

    private Long id;
    private String companyName;
    private String abbreviation;
    private Integer validAccount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 格式化
    private Timestamp expirationTime;
    private Timestamp createTime;

}
