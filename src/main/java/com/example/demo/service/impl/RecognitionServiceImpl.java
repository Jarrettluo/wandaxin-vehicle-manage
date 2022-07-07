package com.example.demo.service.impl;

import com.baidu.aip.imageclassify.AipImageClassify;
import com.baidu.aip.ocr.AipOcr;
import com.example.demo.domain.dto.RecognitionDTO;
import com.example.demo.service.RecognitionService;
import com.example.utils.result.ApiResult;
import lombok.Data;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/28 18:40
 * @Version 1.0
 */
@Service
public class RecognitionServiceImpl implements RecognitionService {

    //设置APPID/AK/SK
    public static final String APP_ID = "23606590";
    public static final String API_KEY = "4nXCAggI1tjzBIgQWBvbjaOM";
    public static final String SECRET_KEY = "wtzGE0rxyfdPvcnzpCs2LNcXrMKjLiqQ";

    public static final String APP_ID_1 = "23607717";
    public static final String API_KEY_1 = "a1gknwjcfBw6HWvVGqtQZDwV";
    public static final String SECRET_KEY_1 = "alT3XfCDX3RDvkID9QY20Q0gjjoFOOB3";

    @Override
    public ApiResult find(byte[] image) {
        if(image.length>10048575) {
            return ApiResult.error(201, "文件过大！");
        }
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        HashMap<String, String> options = new HashMap<String, String>();

        JSONObject res = client.plateLicense(image, options);
        String number;

        if(res.has("words_result")) {
            JSONObject jsonData = res.getJSONObject("words_result");
            number = jsonData.getString("number");
        }else {
            number = null;
        }

        // 初始化一个AipImageClassify
        AipImageClassify client1 = new AipImageClassify(APP_ID_1, API_KEY_1, SECRET_KEY_1);

        // 传入可选参数调用接口
        HashMap<String, String> options1 = new HashMap<String, String>();
        options.put("top_num", "1");

        // 参数为本地路径
        JSONObject res1 = client1.carDetect(image, options1);
        String color;
        String name;
        String year;
        if(res1.has("result")) {
            JSONArray jsonData = res1.getJSONArray("result");
            JSONObject vehicle = jsonData.getJSONObject(0);//获取数组第一个元素
            year = vehicle.getString("year");
            name = vehicle.getString("name");
            color = res1.getString("color_result");
            if(year.equals("无年份信息")){
                year = null;
            }
            if(name.equals("非车类")) {
                name = null;
            }
            if(color.equals("无车辆颜色信息")) {
                color = null;
            }
        }else {
            year = null;
            name = null;
            color = null;
        }

        RecognitionDTO recognitionDTO = new RecognitionDTO(number,
                name,
                year,
                color);
        return ApiResult.success(recognitionDTO);
    }

    /**
     * vin 识别来自于百度的接口：https://ai.baidu.com/ai-doc/OCR/Nkibizxlf#vin%E7%A0%81%E8%AF%86%E5%88%AB
     * @param image
     * @return
     */
    @Override
    public ApiResult vinCode(byte[] image) {
        if(image.length>10048575) {
            return ApiResult.error(201, "文件过大！");
        }
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        HashMap<String, String> options = new HashMap<String, String>();
        JSONObject res = client.vinCode(image, options); // 传入二进制文件
        if(res.has("words_result")) {
            JSONArray vins = res.getJSONArray("words_result");
            JSONObject vinCodeObject = null;
            if(vins != null && vins.length()>0){
                vinCodeObject = vins.getJSONObject(0);
            }
            if(vinCodeObject != null){
                String vinCode = vinCodeObject.getString("words");
                if(!("").equals(vinCode) && vinCode.length()==17){
                    // 进行数据校验
                    ResponseContent responseContent = VinQueryByHttp.queryVin(vinCode);
                    return ApiResult.success(responseContent);
                }
            }
        }
        return ApiResult.error(1203, "识别失败");
    }

    @Override
    public ApiResult searchInfofbyVin(String vin){
        // 进行数据校验
        ResponseContent responseContent = VinQueryByHttp.queryVin(vin);
        return ApiResult.success(responseContent);
    }
}


@Data
class ResponseContent{
    private int status;
    private Object msg;
    private Object data;
    private String vinCode;
}

class VinQueryByHttp{
    static ResponseContent queryVin(String vinCode){
        Map<String,String> map = new HashMap();
        map.put("key","d7ba9fa7634764f2fd5bb81e8183ce18");
        map.put("vin", vinCode);
        String url = "http://118.31.113.49/api/vin/v2/index?key={key}&vin={vin}";
        RestTemplate restTemplate = new RestTemplate();
        //将指定的url返回的参数自动封装到自定义好的对应类对象中
        ResponseContent responseContent = restTemplate.getForObject(url, ResponseContent.class, map);
        responseContent.setVinCode(vinCode);
        if(responseContent.getStatus()==0 && "[]".equals(responseContent.getData()) == false){
            responseContent.setStatus(200);
            return responseContent;
        }
        return responseContent;
    }
}