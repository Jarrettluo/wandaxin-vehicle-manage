package com.example.demo.service.impl;

import com.example.demo.domain.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class TokenServiceImplTest {

    @Autowired
    private TokenServiceImpl tokenService;

    @Test
    void getToken() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("ljr");
        userDTO.setPassword("123456");
        String token = tokenService.getToken(userDTO);

        assertThat(token);

    }
}