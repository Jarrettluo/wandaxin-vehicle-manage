package com.example.demo.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class PreparatoryItemDTO {

    private Long id;

    @NotNull(message = "整备项目的名字不能为空")
    @Size(min = 1, max = 16, message = "整备项目的名字为1-16个字符")
    @Pattern(regexp = "[a-z0-9A-Z\u4e00-\u9fa5]+$", message = "仅支持中文")
    private String name;

    // 默认值为1
    @NotNull(message = "公司主键ID不能为空")
    private Long companyId;

    // 整备项目属于平台或者某个用户"user","default"
    @NotNull(message = "整备项目类型不能为空")
    @Pattern(regexp = "user|default", message = "type 只能是 user 或者 default")
    private String type;
}
