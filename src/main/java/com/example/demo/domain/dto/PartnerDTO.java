package com.example.demo.domain.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/12 14:39
 * @Version 1.0
 */
@Data
public class PartnerDTO {

    private Long id;

    @NotNull(message = "")
    @Size
    private String name;

    @NotNull(message = "")
    @Min(value = 0, message = "必须大于0")
    private Integer price;

    @NotNull(message = "车辆id不能为空")
    @Positive(message = "必须是正数")
    private Long vehicleId;

}
