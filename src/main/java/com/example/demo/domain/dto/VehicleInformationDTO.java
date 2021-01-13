package com.example.demo.domain.dto;

import com.example.demo.domain.po.PartnerPO;
import com.example.demo.domain.po.PreparednessPO;
import com.example.demo.domain.po.SaleItemPO;
import lombok.Data;

import java.sql.Date;
import java.util.List;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/12 14:35
 * @Version 1.0
 */

@Data
public class VehicleInformationDTO {

    private Long id;
    private String vehicleNumber;
    private String vehiclePlate;
    private String vehicleBrand;
    private Date registrationDate;
    private String vehicleColor;
    private Date purchaseDate;
    private Integer purchasePrice;
    private String vehicleNote;
    private Integer repairState;
    private Integer saleState;
    private Long saleitemId;

    // 车辆拥有的合伙人信息，和车辆是一对多的关系
    private List<PartnerDTO> partners;

    // 车辆拥有的整备信息，和车辆是一对多的关系
    private List<PreparednessDTO> preparednesses;

    // 车辆只有一个销售项
    private SaleItemDTO saleItem;

}
