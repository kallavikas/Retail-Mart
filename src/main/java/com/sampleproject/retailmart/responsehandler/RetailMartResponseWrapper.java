package com.sampleproject.retailmart.responsehandler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.sampleproject.retailmart.dto.ResponseWrapper;
import com.sampleproject.retailmart.enums.ResponseMessage;

import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class RetailMartResponseWrapper implements ResponseBodyAdvice<Object> {
	
	private final HttpServletRequest httpServletRequest;
	
	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return !httpServletRequest.getRequestURI().contains("swagger")
				&& !httpServletRequest.getRequestURI().endsWith("/v2/api-docs")
				&& !converterType.equals(StringHttpMessageConverter.class);
	}
	//https://stackoverflow.com/questions/51828879/springboot-controller-return-data-through-responsebodyadvice-occur-an-error-cl

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		
		//Incase of exception return the repsonse
		if(body instanceof ResponseWrapper && ((ResponseWrapper)body).getMessage().equals(ResponseMessage.ERROR))
			return body;
		
		return ResponseWrapper.builder()
				.data(body)
				.message(ResponseMessage.SUCCESS)
				.httpStatusCode(((ServletServerHttpResponse)response).getServletResponse().getStatus())
				.build();
	}

}
