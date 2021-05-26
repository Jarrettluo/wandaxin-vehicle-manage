package com.example.demo.repository;

import com.example.demo.domain.po.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserRepository {

    @Select("SELECT * FROM user where username = #{username}")
    UserPO findByUsername(String username);


    @Select("SELECT * FROM user where id = #{Id}")
    UserPO findUserById(String Id);
}
