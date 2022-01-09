package com.example.demo.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PreparatoryItemDTO {

    private Long id;

    @NotNull(message = "整备项目的名字不能为空")
    @Size(min = 1, max = 16, message = "整备项目的名字不能超过16个字符")
    private String name;

    // 默认值为1
    @NotNull(message = "公司主键ID不能为空")
    @Size(min = 1, max = 9999)
    private Long companyId;

    // 整备项目属于平台或者某个用户"user","default"
    @NotNull(message = "整备项目类型不能为空")
    private String type;
}
