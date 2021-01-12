package com.example.demo.controller;

import com.example.demo.service.RepairItem;
import com.example.demo.service.VehicleRepairFormat;
import com.example.demo.service.VehicleRepairItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.ArrayList;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/10 19:09
 * @Version 1.0
 */

@RestController()
public class Repair {

    @GetMapping("/repairItems/")
    public String getRepairItems(){
        Date date = new Date(System.currentTimeMillis());
        RepairItem repairItem = new RepairItem("洗车", 23, "罗晓娟", date);
        ArrayList<RepairItem> repairItems = new ArrayList<>();
        repairItems.add(repairItem);
        VehicleRepairItem vehicleRepairItem = new VehicleRepairItem("vn123", repairItems);
        VehicleRepairFormat vehicleRepairFormat = new VehicleRepairFormat(200, vehicleRepairItem);
        Gson gson = new GsonBuilder().create();
        String toJson = gson.toJson(vehicleRepairFormat);
        return toJson;
    }

    @PostMapping("/repairItems/")
    public String postRepariItems(VehicleRepairItem vehicleRepairItem) {
        System.out.println(vehicleRepairItem.toString());
        return "kkk";
    }

    // @PostMapping("/c")
    // public String c(User user){
    //     return user.toString();
    // }


}
