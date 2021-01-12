package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/11 15:42
 * @Version 1.0
 */
public interface UserService {

    List<User> findAll();
}
