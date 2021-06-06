package com.example.demo.controller;

import com.example.demo.aop.OperationLogAnnotation;
import com.example.demo.domain.dto.VehicleInformationDTO;
import com.example.demo.service.VehicleService;
import com.example.utils.result.ApiResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/12 15:20
 * @Version 1.0
 */

@RestController
@RequestMapping(value="/vehicle")
public class VehicleController {

    @Resource
    VehicleService vehicleService;

    @OperationLogAnnotation(operModul = "车辆入库模块",operType = "增加",operDesc = "增加新车")
    @CrossOrigin
    @PostMapping
    public ApiResult save(@RequestBody VehicleInformationDTO vehicleInformationDTO) {
//        for(ObjectError error : bindingResult.getAllErrors()){
//            return ApiResult.error(1201, error.getDefaultMessage());
//        }
//        System.out.println(bindingResult);
//        if (bindingResult.hasErrors()) {
//
//            return ApiResult.error(1201, "error.getDefaultMessage()");
//        }
        return vehicleService.save(vehicleInformationDTO);
    }

    @OperationLogAnnotation(operModul = "车辆入库模块",operType = "删除",operDesc = "删除车辆")
    @CrossOrigin
    @DeleteMapping("/{id}")
    public ApiResult remove(@PathVariable Long id) {
        return vehicleService.remove(id);
    }

    @OperationLogAnnotation(operModul = "车辆入库模块",operType = "修改",operDesc = "车辆信息")
    @CrossOrigin
    @PutMapping("/{id}")
    public ApiResult update(@RequestBody VehicleInformationDTO vehicleInformationDTO, @PathVariable Long id){
        vehicleInformationDTO.setId(id);
        return vehicleService.update(vehicleInformationDTO);
    }

    @OperationLogAnnotation(operModul = "车辆入库模块",operType = "查询",operDesc = "车辆信息")
    @CrossOrigin
    @GetMapping("/{id}")
    public ApiResult find(@PathVariable Long id){
        return vehicleService.find(id);
    }

    @OperationLogAnnotation(operModul = "车辆入库模块",operType = "查询",operDesc = "全部车辆")
    @CrossOrigin
    @GetMapping("/list")
    public ApiResult list(){
        return vehicleService.list();
    }

    @OperationLogAnnotation(operModul = "车辆入库模块",operType = "搜索",operDesc = "车辆列表信息")
    @CrossOrigin
    @GetMapping("/search/")
    public ApiResult search(@RequestParam(name = "vehiclePlate") String vehiclePlate){
        return vehicleService.search(vehiclePlate);
    }

}
