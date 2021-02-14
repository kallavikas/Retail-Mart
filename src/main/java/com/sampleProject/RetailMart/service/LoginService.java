package com.sampleProject.RetailMart.service;

import com.sampleProject.RetailMart.dto.LoginDto;
import com.sampleProject.RetailMart.model.Login;

public interface LoginService {
	public Login signUp(LoginDto login);
	public String login(LoginDto login);
	
}
