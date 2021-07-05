package com.example.demo.repository.impl;

import com.example.demo.domain.po.UserPO;
import com.example.demo.repository.UserRepository;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.List;

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

    public List<UserPO> findUserByCompanyId(Long companyId){
        return userRepository.findUserByCompanyId(companyId);
    }

    public Integer save(UserPO userPO){
        return userRepository.save(userPO);
    }

    public void update(UserPO userPO){
        userRepository.update(userPO);
    }
}
