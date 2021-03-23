package com.sampleproject.retailmart.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.sampleproject.retailmart.util.TestUtil;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerIT {
	
	@Autowired 
	private MockMvc mockMvc;
	
	
	@Test
	void helloWorld() throws Exception
	{
		mockMvc.perform(get("/hello-world").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	void login() throws Exception
	{
		mockMvc.perform(post("/login").content(TestUtil.getLoginRequest()).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test 
	void signUp() throws  Exception
	{
		mockMvc.perform(post("/sign-up")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.getSignUpRequest()))
		.andExpect(status().isOk()).andReturn();
	}
	
	



}
