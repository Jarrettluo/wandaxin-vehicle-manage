package com.example.demo.controller;

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
    @CrossOrigin
    @GetMapping("/{vehicleId}")
    public ApiResult list(@PathVariable Long vehicleId){
        return preparedService.list(vehicleId);
    }
    @CrossOrigin
    @PostMapping
    public ApiResult save(@RequestBody PreparednessDTO[] preparednesses){
        return preparedService.save(preparednesses);
    }
    @CrossOrigin
    @DeleteMapping("/{vehicleId}")
    public ApiResult remove(@PathVariable Long vehicleId) {
        return preparedService.remove(vehicleId);
    }

}
