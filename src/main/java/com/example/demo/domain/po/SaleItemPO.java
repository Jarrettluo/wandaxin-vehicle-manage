package com.example.demo.domain.po;

import lombok.Data;

import java.sql.Date;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/12 13:24
 * @Version 1.0
 */
@Data
public class SaleItemPO {

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

    private float mortgageRebate; // 贷款返佣
    private float insuranceRefund; // 保险退费

    private Long vehicleId;

    private Long companyId; // 所属公司id

}
