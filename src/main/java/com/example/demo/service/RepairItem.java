package com.example.demo.service;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/10 19:11
 * @Version 1.0
 */

@Data
@AllArgsConstructor
public class RepairItem {
    public String repairItem;
    public Integer repairPrice;
    public String handlerName;
    public Date handleDate;
}
