package com.sampleProject.RetailMart.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	
	
	@GetMapping("/hello-world")
	public ResponseEntity<String> helloWorld()
	{
		return ResponseEntity.ok("Hi HelloWorld");
	}
	
	

}
