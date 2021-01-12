package com.example.demo.domain.dto;

import lombok.Data;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/12 14:39
 * @Version 1.0
 */
@Data
public class PartnerDTO {

    private Long id;
    private String name;
    private Integer price;

    private Long vehicleId;

}
