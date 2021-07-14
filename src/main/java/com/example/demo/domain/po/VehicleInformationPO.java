package com.example.demo.domain.po;

import com.example.utils.result.PO.AbstractPO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;
import java.util.List;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/12 11:35
 * @Version 1.0
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class VehicleInformationPO extends AbstractPO {

    private String vehiclePlate;
    private String vehicleBrand;
    private Date registrationDate;
    private String vehicleColor;
    private Date purchaseDate;
    private Integer purchasePrice;
    private String vehicleNote;
    private Long saleitemId;

    // 车辆拥有的合伙人信息，和车辆是一对多的关系
    private List<PartnerPO> partners;

    // 车辆拥有的整备信息，和车辆是一对多的关系
    private List<PreparednessPO> preparednesses;

    // 车辆拥有的销售信息，和车辆是一对一的关系
    private SaleItemPO saleItem;

    private Long companyId; // 所属公司id
}
