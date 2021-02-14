package com.sampleProject.RetailMart.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sampleProject.RetailMart.dto.LoginDto;
import com.sampleProject.RetailMart.model.Login;
import com.sampleProject.RetailMart.service.LoginService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController {
	
	private final LoginService loginService;
	
	@GetMapping("/hello-world")
	public ResponseEntity<String> helloWorld()
	{
		return ResponseEntity.ok("Hi HelloWorld!!!");
	}
	
	
	@GetMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDto login)
	{
		return ResponseEntity.ok(loginService.login(login));
	}
	
	@GetMapping("/sign-up")
	public ResponseEntity<Login> signUp(@RequestBody LoginDto login)
	{
		return ResponseEntity.ok(loginService.signUp(login));
	}
	
	

}
