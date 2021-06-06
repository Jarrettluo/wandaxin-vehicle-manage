package com.example.demo.domain.po;

import lombok.Data;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/13 16:34
 * @Version 1.0
 */
@Data
public class StatisticsPO {

    private Float totalSales;
    private Float totalProfit;
    private Integer totalNotSold;
    private Integer totalSold;

}
