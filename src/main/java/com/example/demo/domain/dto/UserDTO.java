package com.example.demo.domain.dto;

import lombok.Data;
import org.codehaus.commons.nullanalysis.NotNull;
import org.intellij.lang.annotations.Pattern;
import org.mybatis.logging.LoggerFactory;
import org.slf4j.Logger;

@Data
public class UserDTO {

    @SuppressWarnings("unused")
    private static final org.slf4j.Logger log = (Logger) LoggerFactory.getLogger(UserDTO.class);


    private Long id;

    @NotNull(message="昵称必须填")
    @Size(min=1, max=20, message="昵称1~20个字")
    private String nickname;

    @NotNull(message="手机号必须填")
    @Pattern(regexp = "^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$", message="账号请输入11位手机号") // 手机号
    private String mobile;


    private String name;

    @NotNull(message="密码必须填")
    @Size(min=6, max=16, message="密码6~16位")
    private String password;
    private String corporationId; // 所属公司的id
    private String telephone;

}
