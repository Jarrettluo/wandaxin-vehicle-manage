package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/11 15:41
 * @Version 1.0
 */
@Data
public class User implements Serializable {
    private Long id; //编号
    private String username; // 用户名
    private String password; // 密码
}
