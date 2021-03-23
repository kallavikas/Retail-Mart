package com.sampleproject.retailmart.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.sampleproject.retailmart.service.LoginService;
import com.sampleproject.retailmart.util.TestUtil;

@WebMvcTest(LoginController.class)
@DisplayName("LoginController Unit test")
class LoginControllerTest {
	
	@Autowired 
	private MockMvc mockMvc;
	
	@MockBean
	private LoginService loginService;
	
	@Test
	void helloWorld() throws Exception
	{
		mockMvc.perform(get("/hello-world").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	void login() throws Exception
	{
		when(loginService.login(Mockito.any())).thenReturn(Mockito.anyString());
		mockMvc.perform(post("/login").content(TestUtil.getLoginRequest()).contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk());
		verify(loginService,times(1)).login(Mockito.any());
		verifyNoMoreInteractions(loginService);
	}
	
	@Test
	void signUp() throws  Exception
	{
		when(loginService.signUp(TestUtil.getLoginResponse())).thenReturn(TestUtil.getLoginResponse());
		MvcResult mvcResult = mockMvc.perform(post("/sign-up")
				 .contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.getLoginRequest()))
		.andExpect(status().isOk()).andReturn();
	    String content = mvcResult.getResponse().getContentAsString();
	    System.out.println("result:"+content);
		verify(loginService,times(1)).signUp(TestUtil.getLoginResponse());
		verifyNoMoreInteractions(loginService);
	}
	
	



}
