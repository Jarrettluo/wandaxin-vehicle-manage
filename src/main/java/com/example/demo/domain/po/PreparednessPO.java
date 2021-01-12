package com.example.demo.domain.po;

import lombok.Data;

import java.sql.Date;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/12 11:44
 * @Version 1.0
 */
@Data
public class PreparednessPO {

    private Long id;
    private String repairItem;
    private Integer repairPrice;
    private String handlerName;
    private Date handleDate;

    private Long vehicleId;

}
