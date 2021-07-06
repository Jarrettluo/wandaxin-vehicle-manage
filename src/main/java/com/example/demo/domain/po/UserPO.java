package com.example.demo.domain.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserPO {
    String id;
    String username;
    String password;
    String type;
    Long companyId;
}
