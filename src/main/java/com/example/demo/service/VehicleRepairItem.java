package com.example.demo.service;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/10 19:15
 * @Version 1.0
 */

@Data
@AllArgsConstructor
public class VehicleRepairItem {
    public String vehicleNum;
    public ArrayList<RepairItem> repairItems;
}
