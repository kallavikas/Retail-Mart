package com.sampleproject.retailmart.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sampleproject.retailmart.enums.ResponseMessage;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonNaming(SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseWrapper {
	@Builder.Default private Instant timestamp=Instant.now();
	private ResponseMessage message;
	private Object data;
	private Integer httpStatusCode;
}
