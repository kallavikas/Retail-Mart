package com.sampleproject.retailmart.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sampleproject.retailmart.dto.LoginDto;
import com.sampleproject.retailmart.entity.Login;

public class TestUtil {
	
	private static final ObjectMapper objMapper=new ObjectMapper();

	public static String getLoginRequest() throws JsonProcessingException {
		return objMapper.writeValueAsString(LoginDto.builder().userName("Lokesh").password("Gupta").mobileNo(24356475l).build());
	}
	
	public static LoginDto getLoginResponse() throws JsonProcessingException {
		return LoginDto.builder().userName("Lokesh").password("Gupta").mobileNo(24356475l).build();
	}
	
	public static Login getLoginModelResponse() throws JsonProcessingException {
		return Login.builder().userName("Lokesh").password("Gupta").mobileNo(24356475l).build();
	}
	
	public static String getSignUpRequest() throws JsonProcessingException {
		return objMapper.writeValueAsString(LoginDto.builder().userName("vikas").password("kalla").mobileNo(24356475l).build());
	}

}
