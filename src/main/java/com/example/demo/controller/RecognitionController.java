package com.example.demo.controller;

import com.example.demo.service.RecognitionService;
import com.example.utils.result.ApiResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/28 18:26
 * @Version 1.0
 */

@RestController
@RequestMapping(value = "/recognition")
public class RecognitionController {

    @Resource
    RecognitionService recognitionService;


    @CrossOrigin
    @PostMapping
    public ApiResult save(@RequestParam(value="file", required=false) MultipartFile multipartFile)
            throws IOException {
        byte[] imgBytes = multipartFile.getBytes();
        if(imgBytes!=null){
            return recognitionService.find(imgBytes);
        }
        else {
            return ApiResult.error(201,"数据为空!");
        }

    }

}
