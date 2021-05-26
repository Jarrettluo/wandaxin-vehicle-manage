package com.example.demo.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.domain.dto.UserDTO;
import com.example.demo.service.TokenService;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Override
    public String getToken(UserDTO userDTO) {
        String token="";
        token= JWT.create().withAudience(userDTO.getId())// 将 user id 保存到 token 里面
                .sign(Algorithm.HMAC256(userDTO.getPassword()));// 以 password 作为 token 的密钥
        return token;
    }
}
