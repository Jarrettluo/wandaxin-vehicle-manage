package com.example.demo.controller;

import com.example.demo.domain.dto.VehicleInformationDTO;
import com.example.demo.service.VehicleService;
import com.example.utils.result.ApiResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    @CrossOrigin
    @PostMapping
    public ApiResult save(@RequestBody VehicleInformationDTO vehicleInformationDTO) {
        System.out.println(vehicleInformationDTO.toString());
        return vehicleService.save(vehicleInformationDTO);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ApiResult remove(@PathVariable Long id) {
        System.out.println(id);
        return vehicleService.remove(id);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ApiResult update(@RequestBody VehicleInformationDTO vehicleInformationDTO, @PathVariable Long id){
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
    public ApiResult list(){
        return vehicleService.list();
    }
}
