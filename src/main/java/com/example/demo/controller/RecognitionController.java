package com.example.demo.controller;

import com.example.demo.service.RecognitionService;
import com.example.utils.result.ApiResult;
import io.swagger.annotations.Api;
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

    @CrossOrigin
    @PostMapping("/vinCode")
    public ApiResult vinCode(@RequestParam(value="file", required=false) MultipartFile multipartFile)
            throws IOException {
        byte[] imgBytes = multipartFile.getBytes();
        if(imgBytes!=null){
            return recognitionService.vinCode(imgBytes);
        }
        else {
            return ApiResult.error(201,"数据为空!");
        }

    }

    @CrossOrigin
    @GetMapping("/searchInfobyVin")
    public ApiResult searchInfofbyVin(@RequestParam(value = "vin") String vin){
        if(vin.length() != 17){
            return ApiResult.error(1201, "提交的vin不对，请重新提交");
        }
        return ApiResult.success();
    }

}
