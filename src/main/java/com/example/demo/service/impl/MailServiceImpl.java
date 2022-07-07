package com.example.demo.service.impl;

import com.example.demo.domain.vo.MailVO;
import com.example.demo.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;


/**
 * @Author Jarrett Luo
 * @Date 2022/7/7 19:39
 * @Version 1.0
 */
@Service
public class MailServiceImpl implements MailService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JavaMailSenderImpl mailSender;

    public MailVO sendMail(MailVO mailVo) {
        try {
            checkMail(mailVo);
            sendMimeMail(mailVo);
            return saveMail(mailVo);
        } catch (Exception e) {
            logger.error("发送邮件失败:", e);
            mailVo.setStatus("fail");
            mailVo.setError(e.getMessage());
            return mailVo;
        }

    }

    private void checkMail(MailVO mailVo) {
        if (StringUtils.hasText(mailVo.getTo())) {
            throw new RuntimeException("邮件收信人不能为空");
        }
        if (StringUtils.hasText(mailVo.getSubject())) {
            throw new RuntimeException("邮件主题不能为空");
        }
        if (StringUtils.hasText(mailVo.getText())) {
            throw new RuntimeException("邮件内容不能为空");
        }
    }

    private void sendMimeMail(MailVO mailVo) {
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true);
            mailVo.setFrom(getMailSendFrom());
            messageHelper.setFrom(mailVo.getFrom());
            messageHelper.setTo(mailVo.getTo().split(","));
            messageHelper.setSubject(mailVo.getSubject());
            messageHelper.setText(mailVo.getText());
            if (!StringUtils.hasText(mailVo.getCc())) {
                messageHelper.setCc(mailVo.getCc().split(","));
            }
            if (!StringUtils.hasText(mailVo.getBcc())) {
                messageHelper.setCc(mailVo.getBcc().split(","));
            }
            if (mailVo.getMultipartFiles() != null) {
                for (MultipartFile multipartFile : mailVo.getMultipartFiles()) {
                    messageHelper.addAttachment(multipartFile.getOriginalFilename(), multipartFile);
                }
            }
            if (StringUtils.hasText(mailVo.getSentDate().toString())) {
                mailVo.setSentDate(new Date());
                messageHelper.setSentDate(mailVo.getSentDate());
            }
            mailSender.send(messageHelper.getMimeMessage());
            mailVo.setStatus("ok");
            logger.info("发送邮件成功：{}->{}", mailVo.getFrom(), mailVo.getTo());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private MailVO saveMail(MailVO mailVo) {

        return mailVo;
    }

    public String getMailSendFrom() {
        return mailSender.getJavaMailProperties().getProperty("from");
    }

}
