package com.sampleproject.retailmart.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sampleproject.retailmart.errorhandler.RetailMartException;
import com.sampleproject.retailmart.repository.LoginRepository;
import com.sampleproject.retailmart.service.impl.LoginServiceImpl;
import com.sampleproject.retailmart.util.TestUtil;

@ExtendWith(SpringExtension.class)
class LoginServiceTest {
	
	@Mock
	private LoginRepository loginRepository;
	
	@Spy
	private ModelMapper modelMapper;
	
	@InjectMocks
	private LoginServiceImpl loginService;
	
	@Test
	@DisplayName("login page test success")
	void login_SucessTest() throws JsonProcessingException
	{
		when(loginRepository.findByUserNameAndPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(Optional.of(TestUtil.getLoginModelResponse()));
		assertEquals("Login Success!!!", loginService.login(TestUtil.getLoginResponse()));
		verify(loginRepository,times(1)).findByUserNameAndPassword(Mockito.anyString(), Mockito.anyString());
		verifyNoMoreInteractions(loginRepository);
	}
	
	
	@Test
	@DisplayName("login page test error scenario")
	void login_Sucess_Error_Scenario_Test() throws JsonProcessingException
	{
		doThrow(InternalServerError.class).when(loginRepository).findByUserNameAndPassword(Mockito.anyString(), Mockito.anyString());
		assertThrows(RetailMartException.class, ()->loginService.login(TestUtil.getLoginResponse()));
		verify(loginRepository,times(1)).findByUserNameAndPassword(Mockito.anyString(), Mockito.anyString());
		verifyNoMoreInteractions(loginRepository);
	}
	
	
}
