package com.example.demo.controller;


import com.example.demo.domain.dto.PreparatoryItemDTO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.Charset;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class PreparednessControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        System.out.println("---------------start---------------");
        System.out.println("================end================");
    }

    /**
     * @Author luojiarui
     * @Description transactional 开启事务功能， rollback 事务回滚，默认是true
     * @Date 10:19 下午 2022/5/4
     * @Param []
     * @return void
     **/
    @Test
    @Transactional
    @Rollback()
    public void getListTest1() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/preparatoryItem/list")
                .param("companyId", "1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // 拿到响应结果
        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString(Charset.defaultCharset());
        System.out.println(result);
        Assert.assertEquals(status, 200);
    }

    @Test
    @Transactional
    @Rollback()
    public void saveTest1() throws Exception {
        PreparatoryItemDTO preparatoryItemDTO = new PreparatoryItemDTO();
        preparatoryItemDTO.setCompanyId(1L);
        preparatoryItemDTO.setName("罗佳瑞");
        preparatoryItemDTO.setType("user");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/preparatoryItem/addItem")
                .content((new Gson()).toJson(preparatoryItemDTO))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // 拿到响应结果
        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString(Charset.defaultCharset());
        System.out.println(result);
        Assert.assertEquals(status, 200);
    }


}