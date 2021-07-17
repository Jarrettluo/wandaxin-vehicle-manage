package com.example.demo.controller;

import com.example.demo.aop.OperationLogAnnotation;
import com.example.demo.domain.dto.PartnerDTO;
import com.example.demo.domain.dto.PreparednessDTO;
import com.example.demo.domain.po.PreparednessPO;
import com.example.demo.service.PreparedService;
import com.example.utils.result.ApiResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/13 14:44
 * @Version 1.0
 */

@RestController
@RequestMapping(value="/preparedness")
public class PreparednessController {

    @Resource
    PreparedService preparedService;

    @OperationLogAnnotation(operModul = "车辆整备信息",operType = "查询",operDesc = "整备信息")
    @CrossOrigin
    @GetMapping("/{vehicleId}")
    public ApiResult list(@PathVariable Long vehicleId){
        return preparedService.list(vehicleId);
    }

    @OperationLogAnnotation(operModul = "车辆整备信息",operType = "新增",operDesc = "整备信息")
    @CrossOrigin
    @PostMapping
    public ApiResult save(@RequestBody PreparednessDTO[] preparednesses) throws IllegalAccessException {
        return preparedService.save(preparednesses);
    }

    @OperationLogAnnotation(operModul = "车辆整备信息",operType = "删除",operDesc = "整备信息")
    @CrossOrigin
    @DeleteMapping("/{vehicleId}")
    public ApiResult remove(@PathVariable Long vehicleId) {
        return preparedService.remove(vehicleId);
    }

}
