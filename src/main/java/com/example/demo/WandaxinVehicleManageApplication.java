package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.example.demo.mapper", "com.example.demo.repository.impl", "com.example.demo.repository"})
public class WandaxinVehicleManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(WandaxinVehicleManageApplication.class, args);
	}

}