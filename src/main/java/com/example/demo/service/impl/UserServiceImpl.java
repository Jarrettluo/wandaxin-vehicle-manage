package com.example.demo.service.impl;


import com.example.demo.domain.dto.UserDTO;
import com.example.demo.domain.po.UserPO;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.impl.UserRepositoryImpl;
import com.example.demo.service.UserService;
import com.example.utils.result.bean.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/11 15:42
 * @Version 1.0
 */

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserRepository userRepository;
    private String userId;

    /**
     * @param userDTO
     * @return
     */
    @Override
    public UserDTO findByUsername(UserDTO userDTO) {
        UserPO userPO = userRepository.findByUsername(userDTO.getUsername());
        UserDTO userDTONew = BeanUtil.mapperBean(userPO, UserDTO.class);
        return userDTONew;
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public UserDTO findUserById(String userId) {
        UserPO userPO = userRepository.findUserById(userId);
        UserDTO userDTO = BeanUtil.mapperBean(userPO, UserDTO.class);
        return userDTO;
    }

}
