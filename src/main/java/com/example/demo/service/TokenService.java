package com.example.demo.service;

import com.example.demo.domain.dto.UserDTO;

public interface TokenService {

    String getToken(UserDTO userDTO);

}
