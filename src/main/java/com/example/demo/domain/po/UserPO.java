package com.example.demo.domain.po;


import lombok.Data;

@Data
public class UserPO {

    private Long id;
    private String name;
    private String password;
    private String corporationId; // 所属公司的id
    private String telephone;

}
