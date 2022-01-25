package com.example.demo;

public class JavaDemoTest {
    public static void main(String[] args) {
        // 写入数据库的数据必须经过正则判断，手机号码，用户密码
        String regex = "^1[3-5|7|8]\\d{9}$";
        // 密码正则限制
        String pwdReg = "^[\\dA-Za-z_]{6,14}$";
        System.out.println("15008201329".matches(regex));
    }
}
