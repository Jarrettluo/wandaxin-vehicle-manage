package com.example.demo.domain.po;

import com.example.utils.result.PO.AbstractPO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
public class VehicleDescriptionPO {

    private Integer id;

    @NotNull(message = "车辆ID不能为空")
    private Long vehicleId;

    private String vinCode;

}
