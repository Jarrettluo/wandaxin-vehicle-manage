package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@MapperScan("com.example.demo.mapper")//使用MapperScan批量扫描所有的Mapper接口；
// @ComponentScan(basePackages = {"com.example.demo.repository.impl"})
@MapperScan("com.example.demo.repository.impl")
@MapperScan("com.example.demo.repository")
@EnableOpenApi
public class WandaxinVehicleManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(WandaxinVehicleManageApplication.class, args);
	}

}