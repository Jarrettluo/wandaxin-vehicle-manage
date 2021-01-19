package com.example.demo.controller;


import com.example.demo.domain.dto.SaleItemDTO;
import com.example.demo.service.SaleItemService;
import com.example.utils.result.ApiResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/13 14:04
 * @Version 1.0
 */
@RestController
@RequestMapping(value="/saleitem")
public class SaleItemController {

    @Resource
    SaleItemService saleItemService;

    @CrossOrigin
    @GetMapping("/{vehicleId}")
    public ApiResult find(@PathVariable Long vehicleId){
        return saleItemService.find(vehicleId);
    }
    @CrossOrigin
    @PostMapping
    public ApiResult save(@RequestBody SaleItemDTO saleItem){
        return saleItemService.save(saleItem);
    }

    @CrossOrigin
    @PutMapping
    public ApiResult update(@RequestBody SaleItemDTO saleItem){
        return saleItemService.update(saleItem);
    }

}
