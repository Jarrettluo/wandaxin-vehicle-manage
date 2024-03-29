package com.example.demo.servicetests;


import com.example.demo.service.RecognitionService;
import com.example.utils.result.ApiResult;
import com.example.utils.result.SuccessApiResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecognitionServiceTests {

    @Autowired
    RecognitionService recognitionService;


    @Test
    public void testRecognition(){
        String vin = "LS5A2DBE5FA003264";
        ApiResult apiResult = recognitionService.searchInfofbyVin(vin);
        if(apiResult.getCode() == 200){
            SuccessApiResult successApiResult = (SuccessApiResult) apiResult;
            System.out.println(successApiResult.getData().toString());
        }
    }


}
