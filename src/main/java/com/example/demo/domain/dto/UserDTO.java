package com.example.demo.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserDTO {

    String id;

    @NotNull(message = "用户名不能为空")
    @Size(min = 1, max = 16, message = "用户名的长度是1-16")
    String username;

    @NotNull(message = "密码不能为空")
    @Size(min = 1, max = 20, message = "密码的长度是1到20")
    String password;
}
