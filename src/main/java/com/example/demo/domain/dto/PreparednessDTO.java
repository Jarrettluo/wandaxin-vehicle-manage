package com.example.demo.domain.dto;

import lombok.Data;

import java.sql.Date;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/12 14:38
 * @Version 1.0
 */
@Data
public class PreparednessDTO {

    private Long id;
    private String repairItem;
    private Integer repairPrice;
    private String handlerName;
    private Date handleDate;

    private Long vehicleId;

}
