package com.sampleProject.RetailMart.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.sampleProject.RetailMart.dto.LoginDto;
import com.sampleProject.RetailMart.model.Login;
import com.sampleProject.RetailMart.repository.LoginRepository;
import com.sampleProject.RetailMart.service.LoginService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{
	
	private final LoginRepository loginRepository;
	
	private final ModelMapper modelMapper;

	@Override
	public Login signUp(LoginDto login) {
		return loginRepository.save(modelMapper.map(login, Login.class));
	}

	@Override
	public String login(LoginDto login) {
		Optional<Login> findByNameAndByPassword = loginRepository.findByUserNameAndPassword(login.getUserName(),login.getPassword());
		if(findByNameAndByPassword.isPresent())
			return "Login Success!!!";
		return "Login Failure!!";
	}
	
	

}
