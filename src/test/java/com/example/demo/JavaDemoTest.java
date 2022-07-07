package com.example.demo;

//import com.mysql.cj.protocol.x.Notice;
import lombok.Data;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Data
class Notice {
    private int status;
    private Object msg;
    private Object data;
}


public class JavaDemoTest {
    public static void main(String[] args) {
//        // 写入数据库的数据必须经过正则判断，手机号码，用户密码
//        String regex = "^1[3-5|7|8]\\d{9}$";
//        // 密码正则限制
//        String pwdReg = "^[\\dA-Za-z_]{6,14}$";
//        System.out.println("15008201329".matches(regex));

        Map<String,String> map = new HashMap();
        map.put("key","d7ba9fa7634764f2fd5bb81e8183ce18");
        map.put("vin","LFV2A21K363553763");
        map.put("vin", "sdfsd");

        // String url = "http://118.31.113.49/api/vin/v2/index?key=d7ba9fa7634764f2fd5bb81e8183ce18&vin=LFV2A21K363553763";
        String url = "http://118.31.113.49/api/vin/v2/index?key={key}&vin={vin}";
        RestTemplate restTemplate = new RestTemplate();
        //将指定的url返回的参数自动封装到自定义好的对应类对象中
        Notice notice = restTemplate.getForObject(url, Notice.class, map);
//        System.out.println(notice);
        System.out.println(notice.toString());
    }
}
