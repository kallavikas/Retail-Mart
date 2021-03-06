package com.sampleproject.retailmart.configuration;


import java.util.Collections;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class RetailMartConfig {
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.sampleproject.retailmart"))              
          .paths(PathSelectors.any()) 
          .build().apiInfo(apiInfo());                                           
    }
	
	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "Retail Mart API's", 
	      "Provide retail mart services", 
	      "API TOS", 
	      "Terms of service URL", 
	      new Contact("vikas kalla", "www.retailmart.com", "rmart@company.com"), 
	      "License of API",
	      "API license URL", 
	      Collections.emptyList());
	    
	}
	
	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}


}
