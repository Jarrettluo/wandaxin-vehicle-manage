package com.example.demo.controller;

import com.example.demo.annotation.UserLoginToken;
import com.example.demo.aop.OperationLogAnnotation;
import com.example.demo.domain.dto.PartnerDTO;
import com.example.demo.service.PartnerService;
import com.example.demo.service.VehicleService;
import com.example.utils.result.ApiResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/13 14:02
 * @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/partner")
public class PartnerController {

    @Resource
    PartnerService partnerService;

    @UserLoginToken
    @OperationLogAnnotation(operModul = "合伙人",operType = "查询",operDesc = "合伙人信息")
    @GetMapping("/{vehicleId}")
    public ApiResult list(@PathVariable Long vehicleId) {
        return partnerService.list(vehicleId);
    }

    @UserLoginToken
    @OperationLogAnnotation(operModul = "合伙人",operType = "新增",operDesc = "合伙人信息")
    @PostMapping
    public ApiResult save(@RequestBody PartnerDTO[] partners) throws IllegalAccessException {
        return partnerService.save(partners);
    }

    @UserLoginToken
    @OperationLogAnnotation(operModul = "合伙人",operType = "删除",operDesc = "合伙人信息")
    @DeleteMapping("/{vehicleId}")
    public ApiResult remove(@PathVariable Long vehicleId) {
        return partnerService.remove(vehicleId);
    }


}
