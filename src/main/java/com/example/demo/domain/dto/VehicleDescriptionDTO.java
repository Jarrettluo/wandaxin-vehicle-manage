package com.example.demo.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class VehicleDescriptionDTO {

    private Integer id;

    @NotNull(message = "车辆ID不能为空")
    private Long vehicleId;

    private String vinCode;
}
