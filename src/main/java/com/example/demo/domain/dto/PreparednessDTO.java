package com.example.demo.domain.dto;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/12 14:38
 * @Version 1.0
 */
@Data
public class PreparednessDTO {

    private Long id;

    @NotNull(message = "repairItem不能为空！")
    private String repairItem;

    private Integer repairPrice;
    private String handlerName;
    private LocalDate handleDate;

    private Long vehicleId;

}
