package com.sampleproject.retailmart.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sampleproject.retailmart.dto.LoginDto;
import com.sampleproject.retailmart.entity.Login;
import com.sampleproject.retailmart.errorhandler.RetailMartException;
import com.sampleproject.retailmart.repository.LoginRepository;
import com.sampleproject.retailmart.service.LoginService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{
	
	private final LoginRepository loginRepository;
	
	private final ModelMapper modelMapper;

	@Override
	public LoginDto signUp(LoginDto login) {
		try {
			return modelMapper.map(loginRepository.save(modelMapper.map(login, Login.class)),LoginDto.class);
		}catch (Exception ex) {
			throw new RetailMartException("Exception Occured while login ", ex,HttpStatus.CONFLICT);
		}
	}

	@Override
	public String login(LoginDto login) {
		try {
		Optional<Login> findByNameAndByPassword = loginRepository.findByUserNameAndPassword(login.getUserName(),login.getPassword());
		if(findByNameAndByPassword.isPresent())
			return "Login Success!!!";
		return "Login Failure!!";
		}
		catch (Exception ex) {
			throw new RetailMartException("Exception Occured while login ", ex,HttpStatus.CONFLICT);
		}
	}
	
	

}
