package com.sampleproject.retailmart.configuration;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RetailMartConfig {
	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}
}
