package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/10 10:24
 * @Version 1.0
 */

@RestController
public class Vehicle {

    @GetMapping("/test")
    public String test() {
        return "{test}";
    }

    @PostMapping("/vehicle1")
    public String vehicle(HttpServletRequest request){
        String value = "";
        String aa = request.getParameter("car");
        String bb = request.getParameter("price");
        value = value + aa + bb;
        return ""+value;
    }
}
