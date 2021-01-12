package com.example.demo.service;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/10 19:17
 * @Version 1.0
 */

@Data
@AllArgsConstructor
public class VehicleRepairFormat {
    public Integer code;
    public VehicleRepairItem vehicleRepairItem;
}
