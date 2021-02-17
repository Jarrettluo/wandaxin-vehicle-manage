package com.example.demo.domain.dto;

import lombok.Data;

import java.sql.Date;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/12 14:39
 * @Version 1.0
 */

@Data
public class SaleItemDTO {

    private Long id;
    private Integer salePrice;
    private double commissionRate;
    private double commissionPrice;
    private Integer repairPrice;
    private Integer partnerPrice;
    private float partnerProfit;
    private float selfProfit;
    private Date saleDate;
    private Integer clearState;

    private Long vehicleId;

}
