package com.example.demo.controller;

import com.example.demo.aop.OperationLogAnnotation;
import com.example.demo.domain.dto.VehicleInformationDTO;
import com.example.demo.service.VehicleService;
import com.example.utils.result.ApiResult;
import io.swagger.annotations.Api;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.regex.Pattern;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/12 15:20
 * @Version 1.0
 */

@RestController
@RequestMapping(value="/vehicle")
public class VehicleController {
    private String pattern = "^[A-HJ-NPR-Z\\d]{17}$";
    @Resource
    VehicleService vehicleService;

    @OperationLogAnnotation(operModul = "车辆入库信息",operType = "增加",operDesc = "增加新车")
    @CrossOrigin
    @PostMapping
    public ApiResult save(@Valid @RequestBody VehicleInformationDTO vehicleInformationDTO) {
        String vinCode = vehicleInformationDTO.getVinCode();
        if(!"".equals(vinCode) && vinCode != null){
            boolean isMatch = Pattern.matches(pattern, vinCode);
            if(!isMatch) {
                return ApiResult.error(1202, "VIN格式不对！");
            }
        }
        return vehicleService.save(vehicleInformationDTO);
    }

    @OperationLogAnnotation(operModul = "车辆入库信息",operType = "删除",operDesc = "删除车辆")
    @CrossOrigin
    @DeleteMapping("/{id}")
    public ApiResult remove(@PathVariable Long id) {
        return vehicleService.remove(id);
    }

    @OperationLogAnnotation(operModul = "车辆入库信息",operType = "修改",operDesc = "车辆信息")
    @CrossOrigin
    @PutMapping("/{id}")
    public ApiResult update(@RequestBody VehicleInformationDTO vehicleInformationDTO, @PathVariable Long id){
        String vinCode = vehicleInformationDTO.getVinCode();
        if(!"".equals(vinCode) && vinCode != null){
            boolean isMatch = Pattern.matches(pattern, vinCode);
            if(!isMatch) {
                return ApiResult.error(1202, "VIN格式不对！");
            }
        }
        vehicleInformationDTO.setId(id);
        return vehicleService.update(vehicleInformationDTO);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ApiResult find(@PathVariable Long id){
        return vehicleService.find(id);
    }

    @CrossOrigin
    @GetMapping("/list")
    public ApiResult list(@RequestParam("companyId") Long companyId, @RequestParam("sellState") String sellState){
        return vehicleService.list(companyId, sellState);
    }

    @OperationLogAnnotation(operModul = "车辆入库信息",operType = "搜索",operDesc = "车辆列表信息")
    @CrossOrigin
    @GetMapping("/search/")
    public ApiResult search(@RequestParam(name = "vehiclePlate") String vehiclePlate, @RequestParam(name = "companyId") Long companyId){
        return vehicleService.search(vehiclePlate, companyId);
    }

}
