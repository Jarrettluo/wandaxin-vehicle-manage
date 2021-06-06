package com.example.demo.controller;

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
@RestController
@RequestMapping(value = "/partner")
public class PartnerController {

    @Resource
    PartnerService partnerService;

    @OperationLogAnnotation(operModul = "合伙人模块",operType = "查询",operDesc = "合伙人信息")
    @CrossOrigin
    @GetMapping("/{vehicleId}")
    public ApiResult list(@PathVariable Long vehicleId) {
        return partnerService.list(vehicleId);
    }

    @OperationLogAnnotation(operModul = "合伙人模块",operType = "新增",operDesc = "合伙人信息")
    @CrossOrigin
    @PostMapping
    public ApiResult save(@RequestBody PartnerDTO[] partners) throws IllegalAccessException {
        return partnerService.save(partners);
    }

    @OperationLogAnnotation(operModul = "合伙人模块",operType = "删除",operDesc = "合伙人信息")
    @CrossOrigin
    @DeleteMapping("/{vehicleId}")
    public ApiResult remove(@PathVariable Long vehicleId) {
        return partnerService.remove(vehicleId);
    }


}
