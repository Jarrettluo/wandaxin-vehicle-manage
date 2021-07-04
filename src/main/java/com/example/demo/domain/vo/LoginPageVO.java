package com.example.demo.domain.vo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class LoginPageVO {

    private String token; // 用户的token
    private String userId; // 登录用户的id
    private String userName; // 登录用户的用户名
    private String companyName; // 公司名
    private String companyAbbreviation; // 公司简称信息
    private Timestamp expirationTime; // 该公司的账号失效时间

}
