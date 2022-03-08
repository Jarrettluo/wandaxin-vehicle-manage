package com.example.demo.service;

import com.example.utils.result.ApiResult;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/28 18:39
 * @Version 1.0
 */
public interface RecognitionService {

    ApiResult find(byte[] image);
    ApiResult vinCode(byte[] image);
    ApiResult searchInfofbyVin(String vin);

}
