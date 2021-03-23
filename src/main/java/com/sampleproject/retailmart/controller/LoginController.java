package com.sampleproject.retailmart.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sampleproject.retailmart.annotation.Traceable;
import com.sampleproject.retailmart.dto.LoginDto;
import com.sampleproject.retailmart.entity.Login;
import com.sampleproject.retailmart.service.LoginService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController {
	
	private final LoginService loginService;
	
	 @ApiOperation(value = "View a list of available products")
	    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Successfully retrieved hello world message"),
	            @ApiResponse(code = 500, message = "Error occured while reading fetch hello world"),
	    })
	@GetMapping("/hello-world")
	@Traceable
	public ResponseEntity<List<String>> helloWorld()
	{
		return ResponseEntity.ok(Arrays.asList("Hi HelloWorld!!!"));
	}
	
	 @ApiOperation(value = "Login api ",response = String.class)
	    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Successfully retrieved list"),
	            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	    }
	    )
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDto login)
	{
		return ResponseEntity.ok(loginService.login(login));
	}
	
	 @ApiOperation(value = "Sign up for Login page",response = Login.class)
	    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Successfully retrieved list"),
	            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	    }
	    )
	@PostMapping("/sign-up")
	public ResponseEntity<LoginDto> signUp(@Valid @RequestBody LoginDto login)
	{
		return ResponseEntity.ok(loginService.signUp(login));
	}
	
	

}
