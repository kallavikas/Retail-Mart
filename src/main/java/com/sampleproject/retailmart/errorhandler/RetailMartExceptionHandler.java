package com.sampleproject.retailmart.errorhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sampleproject.retailmart.dto.ResponseWrapper;
import com.sampleproject.retailmart.enums.ResponseMessage;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class RetailMartExceptionHandler {

	@ExceptionHandler(RetailMartException.class)
	public ResponseEntity<ResponseWrapper> handleCheckedException(RetailMartException ex)
	{
		ResponseWrapper errorResponse=
				ResponseWrapper.builder()
				.httpStatusCode(ex.getHttpStatus().value())
				.message(ResponseMessage.ERROR)
				.data(ex.getMessage())
				.build();
		log.error("Retail Mart custom exception:",ex);
		return new ResponseEntity<>(errorResponse,ex.getHttpStatus());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseWrapper> handleUnCheckedException(Exception ex)
	{
		ResponseWrapper errorResponse=
				ResponseWrapper.builder()
				.httpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.message(ResponseMessage.ERROR)
				.data(ex.getMessage())
				.build();
		log.error("Retail Mart Exception:",ex);
		return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
