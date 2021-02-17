package com.example.demo.service;

import com.example.utils.result.ApiResult;
import java.io.File;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/28 18:39
 * @Version 1.0
 */
public interface RecognitionService {

    ApiResult find(byte[] image);

}
