package com.example.demo.config;

import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Map;
import java.util.Properties;

/**
 * @Author Jarrett Luo
 * @Date 2022/7/7 19:43
 * @Version 1.0
 */
@Configuration
@ConditionalOnProperty(prefix = "spring.mail", name = "host")
class MailSenderPropertiesConfiguration {

    private final MailProperties properties;

    public MailSenderPropertiesConfiguration(MailProperties properties) {
        this.properties = properties;
    }
    @Bean
    @ConditionalOnMissingBean
    public JavaMailSenderImpl mailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        applyProperties(sender);
        return sender;
    }

    private void applyProperties(JavaMailSenderImpl sender) {
        //设置Host
        sender.setHost(this.properties.getHost());
        if (this.properties.getPort() != null) {
            sender.setPort(this.properties.getPort());
        }
        //设置账户
        sender.setUsername(this.properties.getUsername());
        //设置密码--通常是服务授权码
        sender.setPassword(this.properties.getPassword());
        //设置protocol 如smtp pope imap
        sender.setProtocol(this.properties.getProtocol());
        //字符编码，默认UTF-8
        if (this.properties.getDefaultEncoding() != null) {
            sender.setDefaultEncoding(this.properties.getDefaultEncoding().name());
        }
        //其他属性
        if (!this.properties.getProperties().isEmpty()) {
            sender.setJavaMailProperties(asProperties(this.properties.getProperties()));
        }
    }

    private Properties asProperties(Map<String, String> source) {
        Properties properties = new Properties();
        properties.putAll(source);
        return properties;
    }

    /**
     * Condition to trigger the creation of a {@link JavaMailSenderImpl}. This kicks in if
     * either the host or jndi name property is set.
     */
    static class MailSenderCondition extends AnyNestedCondition {

        MailSenderCondition() {
            super(ConfigurationPhase.PARSE_CONFIGURATION);
        }

        @ConditionalOnProperty(prefix = "spring.mail", name = "host")
        static class HostProperty {

        }

        @ConditionalOnProperty(prefix = "spring.mail", name = "jndi-name")
        static class JndiNameProperty {

        }

    }
}