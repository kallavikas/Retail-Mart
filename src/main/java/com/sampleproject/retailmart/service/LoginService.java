package com.sampleproject.retailmart.service;

import com.sampleproject.retailmart.dto.LoginDto;
import com.sampleproject.retailmart.entity.Login;

public interface LoginService {
	public LoginDto signUp(LoginDto login);
	public String login(LoginDto login);
	
}
