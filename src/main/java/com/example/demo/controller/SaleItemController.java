package com.example.demo.controller;


import com.example.demo.annotation.UserLoginToken;
import com.example.demo.aop.OperationLogAnnotation;
import com.example.demo.domain.dto.SaleItemDTO;
import com.example.demo.domain.po.SaleItemPO;
import com.example.demo.service.SaleItemService;
import com.example.utils.result.ApiResult;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/13 14:04
 * @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/saleitem")
public class SaleItemController {

    @Resource
    SaleItemService saleItemService;

    @UserLoginToken
    @OperationLogAnnotation(operModul = "车辆销售信息",operType = "查询",operDesc = "车辆信息")
    @GetMapping("/{vehicleId}")
    public ApiResult find(@PathVariable Long vehicleId) {
        return saleItemService.find(vehicleId);
    }

    @UserLoginToken
    @OperationLogAnnotation(operModul = "车辆销售信息",operType = "新增",operDesc = "销售信息")
    @PostMapping
    public ApiResult save(@RequestBody SaleItemDTO saleItem) throws IllegalAccessException {
        return saleItemService.save(saleItem);
    }

    @UserLoginToken
    @OperationLogAnnotation(operModul = "车辆销售信息",operType = "修改",operDesc = "销售信息")
    @PutMapping
    public ApiResult update(@RequestBody SaleItemDTO saleItem) throws IllegalAccessException {
        return saleItemService.update(saleItem);
    }

    @UserLoginToken
    @OperationLogAnnotation(operModul = "车辆销售信息",operType = "删除",operDesc = "销售信息")
    @DeleteMapping
    public ApiResult remove(@RequestBody SaleItemDTO saleItemDTO) {
        return saleItemService.remove(saleItemDTO.getId());
    }

}
