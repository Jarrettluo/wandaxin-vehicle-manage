package com.example.demo.controller;

import com.example.demo.annotation.UserLoginToken;
import com.example.demo.aop.OperationLogAnnotation;
import com.example.demo.domain.dto.PartnerDTO;
import com.example.demo.domain.dto.PreparednessDTO;
import com.example.demo.domain.po.PreparednessPO;
import com.example.demo.service.PreparedService;
import com.example.utils.result.ApiResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/13 14:44
 * @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping(value="/preparedness")
public class PreparednessController {

    @Resource
    PreparedService preparedService;

    @UserLoginToken
    @OperationLogAnnotation(operModul = "车辆整备信息",operType = "查询",operDesc = "整备信息")
    @GetMapping("/{vehicleId}")
    public ApiResult list(@PathVariable Long vehicleId){
        return preparedService.list(vehicleId);
    }


    @UserLoginToken
    @OperationLogAnnotation(operModul = "车辆整备信息",operType = "新增",operDesc = "整备信息")
    @PostMapping
    public ApiResult save(@RequestBody PreparednessDTO[] preparedness) throws IllegalAccessException {
        return preparedService.save(preparedness);
    }

    @UserLoginToken
    @OperationLogAnnotation(operModul = "车辆整备信息",operType = "删除",operDesc = "整备信息")
    @DeleteMapping("/{vehicleId}")
    public ApiResult remove(@PathVariable Long vehicleId) {
        return preparedService.remove(vehicleId);
    }

}
