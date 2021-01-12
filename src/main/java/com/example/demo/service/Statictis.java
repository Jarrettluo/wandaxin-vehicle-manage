package com.example.demo.service;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/10 18:49
 * @Version 1.0
 */

@Data
@AllArgsConstructor
public class Statictis {
    public Integer totalSales;
    public Integer totalProfit;
    public Integer totalNotSold;
    public Integer totalSold;
}