package com.example.demo;

import com.example.demo.controller.CompanyController;
import com.mysql.cj.protocol.x.Notice;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

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

	@Test
	public void TestHttpRequest(){
		String url = "http://118.31.113.49/api/vin/v2/index?key=d7ba9fa7634764f2fd5bb81e8183ce18&vin=LFV2A21K363553763";
		RestTemplate restTemplate = new RestTemplate();
		//将指定的url返回的参数自动封装到自定义好的对应类对象中
		Notice notice = restTemplate.getForObject(url,Notice.class);
		System.out.println(notice);
	}


}
