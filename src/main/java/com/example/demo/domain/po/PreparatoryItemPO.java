package com.example.demo.domain.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
public class PreparatoryItemPO {

    private Long id;

    @NotNull(message = "整备项目的名字不能为空")
    private String name;

    @NotNull(message = "公司主键ID不能为空")
    private Long companyId;

    // 整备项目属于平台或者某个用户"user","default"
    @NotNull(message = "整备项目类型不能为空")
    private String type;


}
