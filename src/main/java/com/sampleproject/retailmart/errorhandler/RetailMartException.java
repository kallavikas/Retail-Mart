package com.sampleproject.retailmart.errorhandler;

import org.springframework.http.HttpStatus;

public class RetailMartException extends RuntimeException {
   
	private static final long serialVersionUID = 5942942512026439751L;
	
	private final HttpStatus status;
	
    public RetailMartException(String message, Throwable cause,HttpStatus status) {
    	super(message, cause);
        this.status = status;
    }
    public RetailMartException(String message,HttpStatus status) {
    	super(message);
        this.status = status;
    }
    public RetailMartException(Throwable cause,HttpStatus status) {
    	super(cause);
        this.status = status;
    }
    
    public HttpStatus getHttpStatus()
    {
    	return this.status;
    }
    
}
