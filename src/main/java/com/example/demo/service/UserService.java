package com.example.demo.service;

import com.example.demo.domain.dto.UserDTO;
import com.example.utils.result.ApiResult;
import org.springframework.stereotype.Service;

@Service("UserService")
public interface UserService {

    ApiResult findByUsername(UserDTO userDTO);

    UserDTO findUserById(String userId);

}
