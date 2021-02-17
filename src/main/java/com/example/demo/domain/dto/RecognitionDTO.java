package com.example.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/28 18:42
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class RecognitionDTO {

    private String licenseNumber;
    private String name;
    private String year;
    private String color;
}
