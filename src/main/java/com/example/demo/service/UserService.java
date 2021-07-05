package com.example.demo.service;

import com.example.demo.domain.dto.UserDTO;
import com.example.demo.domain.po.UserPO;
import com.example.utils.result.ApiResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserService")
public interface UserService {

    ApiResult findByUsername(UserDTO userDTO);

    UserDTO findUserById(String userId);

    ApiResult list(Long companyId);

}
