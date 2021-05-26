package com.example.demo.repository.impl;

import com.example.demo.domain.po.UserPO;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;

@Repository
public class UserRepositoryImpl implements UserRepository{

    @Resource
    UserRepository userRepository;

    public UserPO findByUsername(String username){
        return userRepository.findByUsername(username);
    };

    public UserPO findUserById(String id){
        return userRepository.findUserById(id);
    };


}
