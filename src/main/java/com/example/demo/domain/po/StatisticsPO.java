package com.example.demo.domain.po;

import lombok.Data;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/13 16:34
 * @Version 1.0
 */
@Data
public class StatisticsPO {

    private Integer totalSales;
    private Integer totalProfit;
    private Integer totalNotSold;
    private Integer totalSold;

}
