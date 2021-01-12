package com.example.demo.domain.po;

import lombok.Data;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/12 11:43
 * @Version 1.0
 */
@Data
public class PartnerPO {

    private Long id;
    private String name;
    private Integer price;
    private Long vehicleId;


}
