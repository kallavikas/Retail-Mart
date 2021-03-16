package com.sampleproject.retailmart.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.sampleproject.retailmart.dto.LoginDto;
import com.sampleproject.retailmart.entity.Login;
import com.sampleproject.retailmart.repository.LoginRepository;
import com.sampleproject.retailmart.service.LoginService;

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
