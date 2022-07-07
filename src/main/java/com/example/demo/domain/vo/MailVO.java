package com.example.demo.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @Author Jarrett Luo
 * @Date 2022/7/7 18:03
 * @Version 1.0
 */
@Data
public class MailVO {
    private String id;
    private String from;
    private String to;
    private String subject;
    private String text;
    private Date sentDate;
    private String cc;
    private String bcc;
    private String status;
    private String error;

    @JsonIgnore
    private MultipartFile[] multipartFiles;

}
