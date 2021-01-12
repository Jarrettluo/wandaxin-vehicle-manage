package com.example.demo.controller;

import com.example.demo.service.Statictis;
import com.example.demo.service.StatictisFormat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/10 18:53
 * @Version 1.0
 */

@RestController
public class SaleStatictis {

    @GetMapping("/salestatictis")
    public String saleStatictis(){
        Statictis statictis = new Statictis(123, 456, 89, 89);
        StatictisFormat statictisFormat = new StatictisFormat(200, statictis);
        Gson gson = new GsonBuilder().create();
        String toJson = gson.toJson(statictisFormat);
        return toJson;
    }
}
