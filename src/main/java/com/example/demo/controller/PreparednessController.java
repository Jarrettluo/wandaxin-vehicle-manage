package com.example.demo.controller;

import com.example.demo.domain.dto.PartnerDTO;
import com.example.demo.domain.dto.PreparednessDTO;
import com.example.demo.domain.po.PreparednessPO;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/13 14:44
 * @Version 1.0
 */

@RestController
@RequestMapping(value="/preparedness")
public class PreparednessController {

    @GetMapping("/{vehicleId}")
    public String find(@PathVariable Long vehicleId){
        return vehicleId.toString();
    }

    @PostMapping("/{vehicleId}")
    public String save(@RequestBody PreparednessDTO[] preparednesses, @PathVariable Long vehicleId){

        return preparednesses.toString();
    }

    @PutMapping("/{vehicleId}")
    public String update(@RequestBody PreparednessDTO[] preparednesses, @PathVariable Long vehicleId){
        return vehicleId.toString();
    }

}
