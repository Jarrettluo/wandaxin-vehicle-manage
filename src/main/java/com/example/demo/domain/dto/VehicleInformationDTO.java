package com.example.demo.domain.dto;

import com.example.demo.domain.po.PartnerPO;
import com.example.demo.domain.po.PreparednessPO;
import com.example.demo.domain.po.SaleItemPO;
import lombok.Data;

import javax.validation.constraints.*;
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

    @NotNull(message = "车牌号不能为空")
    @Size(min = 1, max = 10, message="车牌号长度必须是1-10个字符")
    private String vehiclePlate;

    private String vehicleBrand;

    @Past(message = "注册时间必须是过去的时间") // 必须是过去的时间
    private Date registrationDate;

    private String vehicleColor;

    @PastOrPresent(message = "购买时间不能是未来时间") // 过去获取现在时间
    private Date purchaseDate;

    @NotNull(message = "购买价格不能为空")
    @Min(value = 0, message = "购买价格必须大于0")
    private Integer purchasePrice;

    private String vehicleNote;

    private Long saleitemId;

    // 车辆拥有的合伙人信息，和车辆是一对多的关系
    private List<PartnerDTO> partners;

    // 车辆拥有的整备信息，和车辆是一对多的关系
    private List<PreparednessDTO> preparednesses;

    // 车辆只有一个销售项
    private SaleItemDTO saleItem;

    private Long companyId; // 所属公司id

}
