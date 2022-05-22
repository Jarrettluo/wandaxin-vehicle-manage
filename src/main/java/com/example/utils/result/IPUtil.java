package com.example.utils.result;


//
//import com.example.demo.service.impl.ResponseContent;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class IPUtil {

    // 验证ip地址的正则表达式
    public static final String IP_REG = "((25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))";

    // ip地址查询的URl地址
    public static final String IP_QUERY_URL = "http://ip.ws.126.net/ipquery?ip={ip}";

    /**
     * 获取Ip地址
     *
     * @param request
     * @return
     */
    public static String getIpAdrress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if(StringUtils.isNotBlank(ip)) {
            // 多个路由时，取第一个非unknown的ip
            final String[] arr = ip.split(",");
            for (final String str : arr) {
                if (!"unknown".equalsIgnoreCase(str)) {
                    ip = str;
                    break;
                }
            }
        }
        return ip;
    }

    /**
     * @Author luojiarui
     * @Description ip地址转地区
     * @Date 4:58 下午 2022/5/21
     * @Param [ip]
     * @return java.lang.String
     **/
    public static String ip2location(String ip) {
        if(!Pattern.matches(IP_REG, ip)){
            return null;
        }
        Map<String, String> map = new HashMap<String, String>(5){{
            put("ip", ip);
        }};
        RestTemplate restTemplate = new RestTemplate();
        //将指定的url返回的参数自动封装到自定义好的对应类对象中
        String responseContent = restTemplate.getForObject(IP_QUERY_URL, String.class, map);
        responseContent = responseContent.substring(responseContent.indexOf("{"),
                responseContent.indexOf("}")+1);
        JSONObject jsonObject = JSONObject.parseObject(responseContent);
        return jsonObject.get("city") + " " + jsonObject.get("province");
    }

    public static void main(String[] args) {
        System.out.println(ip2location("56.23.52.41"));
    }

}