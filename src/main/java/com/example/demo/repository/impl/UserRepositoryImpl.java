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

    @Override
    public UserPO findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    public UserPO findUserById(String id){
        return userRepository.findUserById(id);
    }

    @Override
    public List<UserPO> findUserByCompanyId(Long companyId){
        return userRepository.findUserByCompanyId(companyId);
    }

    @Override
    public Integer save(UserPO userPO){
        return userRepository.save(userPO);
    }

    @Override
    public void update(UserPO userPO){
        userRepository.update(userPO);
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteUser(userId);
    }

}
