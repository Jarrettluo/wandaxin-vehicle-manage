package com.example.demo;

import com.example.demo.controller.CompanyController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.sun.tools.doclint.Entity.not;
import static org.assertj.core.api.AssertionsForClassTypes.not;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
class WandaxinVehicleManageApplicationTests {

	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	/**
	 * 获取最新app信息
	 */
	@Test
	public void TestGetAppLatestVersion() throws Exception{
		RequestBuilder request = null;
		//构造请求
		request = post("/company/")
				.param("appId", "1001");
		//执行请求
		mockMvc.perform(request)
				.andExpect(status().isOk())//返回HTTP状态为200
				.andExpect((ResultMatcher) jsonPath("$.status", not("E")))//使用jsonPath解析JSON返回值，判断具体的内容, 此处不希望status返回E
				.andDo(print());//打印结果
		//.andReturn();//想要返回结果，使用此方法

	}


}
