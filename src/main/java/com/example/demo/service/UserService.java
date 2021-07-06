package com.example.demo.service;

import com.example.demo.domain.dto.UserDTO;
import com.example.demo.domain.po.UserPO;
import com.example.utils.result.ApiResult;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("UserService")
public interface UserService {

    ApiResult findByUsername(UserDTO userDTO);

    UserDTO findUserById(String userId);

    ApiResult list(Long companyId);

    ApiResult changePwd(Map<String, String> pwdMap);

    ApiResult changeType(Map<String, String> type);

}
