package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/11 15:42
 * @Version 1.0
 */

@Mapper // 指定这是一个操作数据库的mapper
public interface UserMapper {
    List<User> findAll();
}
